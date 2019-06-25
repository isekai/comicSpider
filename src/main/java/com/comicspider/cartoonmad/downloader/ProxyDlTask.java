package com.comicspider.cartoonmad.downloader;

import com.comicspider.config.GlobalConfig;
import com.comicspider.entity.Proxy;
import com.comicspider.exception.SpiderException;
import com.comicspider.service.ProxyService;
import com.comicspider.utils.HttpUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 代理爬虫线程任务
 * @Author doctor
 * @Date 19-6-3
 **/
@Slf4j
public class ProxyDlTask implements Runnable {
    @Setter
    private String url;
    @Setter
    private ProxyService proxyService;

    public ProxyDlTask(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @Override
    public void run() {
        try {
            String html=new String(HttpUtil.get(url));
            List<Proxy> proxies= getProxy(html);
            for (Proxy proxy : proxies){
                if (proxyService.findByIp(proxy.getIp())==null){
                    try {
                         HttpUtil.get(GlobalConfig.PROXY_TEST_URL, proxy);
                         proxyService.saveOrUpdate(proxy);
                    }
                    catch (SpiderException e){
                        log.info("获取代理ip"+proxy.getIp()+"失败");
                        log.info(e.getMsg());
                    }
                }
            }
        } catch (SpiderException e){
            log.info("爬取代理失败："+e.getMsg());
        }

    }

    private List<Proxy> getProxy(String html){
        List<Proxy> proxies=new ArrayList<>();
        Proxy proxy;
        Document doc= Jsoup.parse(html);
        Elements elements = doc.getElementsByTag("tr");
        for (int i=1;i<elements.size();i++){
            proxy=new Proxy();
            proxy.setIp(elements.get(i).child(1).text());
            proxy.setPort(Integer.parseInt(elements.get(i).child(2).text()));
            proxy.setType(elements.get(i).child(5).text());
            proxies.add(proxy);
        }
        return proxies;
    }
}
