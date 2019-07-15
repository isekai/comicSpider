package com.comicspider.utils;

import com.comicspider.config.GlobalConfig;
import com.comicspider.entity.Proxy;
import com.comicspider.enums.ExceptionEnum;
import com.comicspider.exception.SpiderException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;

@Slf4j
public class HttpUtil {

    private static SSLContext createIgnoreVerifySSL() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return sc;
    }

    private static CloseableHttpClient getHttpClient(){
        SSLContext sc=createIgnoreVerifySSL();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sc))
                .build();
        PoolingHttpClientConnectionManager cm=new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(20);
        return HttpClients.custom().setConnectionManager(cm).build();
    }

    public static byte[] getByte(String requestUrl, Proxy proxy, Header[] headers){
        HttpHost host=null;
        if (proxy!=null){
            host=new HttpHost(proxy.getIp(),proxy.getPort(),proxy.getType());
        }
        RequestConfig requestConfig=RequestConfig.custom()
                .setProxy(host)
                .setConnectTimeout(10000).setConnectionRequestTimeout(5000)
                .setSocketTimeout(10000)
                .build();
        CloseableHttpClient httpClient=getHttpClient();
        try {
            HttpGet httpGet=new HttpGet(requestUrl);
            httpGet.setConfig(requestConfig);
            httpGet.setHeaders(headers);
            CloseableHttpResponse response= httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode()==200){
                return EntityUtils.toByteArray(response.getEntity());
            }
            response.close();
        } catch (IOException e) {
            throw new SpiderException(ExceptionEnum.CONNECT_TIMEOUT.getCode(),e.getMessage());
        }
        return new byte[0];
    }

    public static byte[] get(String requestUrl){
        Header[] headers={new BasicHeader("User-Agent",GlobalConfig.USER_AGENT)};
        return getByte(requestUrl, null, headers);
    }

    public static byte[] get(String requestUrl,Proxy proxy){
        Header[] headers={new BasicHeader("User-Agent",GlobalConfig.USER_AGENT)};
        return getByte(requestUrl, proxy, headers);
    }

    public static byte[] get(String requestUrl, Proxy proxy, Map<String,String> headerMap){
        Header[] headers=new Header[headerMap.size()+1];
        headers[0]=new BasicHeader("User-Agent",GlobalConfig.USER_AGENT);
        int i=1;
        for (String key : headerMap.keySet()){
            headers[i]=new BasicHeader(key, headerMap.get(key));
            i++;
        }
        return getByte(requestUrl, proxy, headers);
    }


}
