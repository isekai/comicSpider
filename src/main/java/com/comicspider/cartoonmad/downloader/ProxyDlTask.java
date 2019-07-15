package com.comicspider.cartoonmad.downloader;

import com.comicspider.config.GlobalConfig;
import com.comicspider.entity.Proxy;
import com.comicspider.enums.ProxyEnum;
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
    private ProxyEnum proxyEnum;
    @Setter
    private ProxyService proxyService;

    public ProxyDlTask(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @Override
    public void run() {
        try {
            String html=new String(HttpUtil.get(proxyEnum.getUrl()));
            getProxy(html);
        } catch (SpiderException e){
            log.info("爬取代理失败："+e.getMsg());
        }

    }

    private void getProxy(String html){
        Proxy proxy;
        Document doc= Jsoup.parse(html);
        Elements elements = doc.getElementsByTag("tr");
        int ip,port,type;
        if (proxyEnum.getCode()==0){
            ip=1;port=2;type=5;
        } else {
            ip=0;port=1;type=3;
        }
        for (int i=1;i<elements.size();i++){
            proxy=new Proxy();
            proxy.setIp(elements.get(i).child(ip).text());
            proxy.setPort(Integer.parseInt(elements.get(i).child(port).text()));
            proxy.setType(elements.get(i).child(type).text());
            try {
                HttpUtil.get(GlobalConfig.PROXY_TEST_URL, proxy);
            } catch (SpiderException e){
                log.info("爬取ip："+proxy.getIp()+"失败！"+e.getMsg());
                continue;
            }
            log.info("爬取ip成功！");
            proxyService.saveOrUpdate(proxy);
        }
    }

}
