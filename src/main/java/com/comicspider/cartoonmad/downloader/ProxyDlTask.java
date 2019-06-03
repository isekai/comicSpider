package com.comicspider.cartoonmad.downloader;

import com.comicspider.cartoonmad.parser.HtmlParser;
import com.comicspider.entity.Proxy;
import com.comicspider.service.ProxyService;
import com.comicspider.utils.HttpUtil;
import lombok.Setter;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-6-3
 **/
public class ProxyDlTask implements Runnable {
    @Setter
    private String taskId;
    @Setter
    private String url;
    @Setter
    private ProxyService proxyService;

    public ProxyDlTask(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @Override
    public void run() {
        String html=new String(HttpUtil.getByte(url, null));
        List<Proxy> proxies= HtmlParser.getProxy(html);
        for (Proxy proxy : proxies){
            if (HttpUtil.getByte("http://httpbin.org/get", proxy).length!=0){
                proxyService.saveOrUpdate(proxy);
            }
        }
    }
}
