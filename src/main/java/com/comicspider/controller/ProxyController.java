package com.comicspider.controller;

import com.comicspider.cartoonmad.downloader.ProxyDlTask;
import com.comicspider.enums.ProxyEnum;
import com.comicspider.service.ProxyService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;


import java.util.concurrent.*;


/**
 * @Author doctor
 * @Date 19-6-3
 **/
@RestController
@RequestMapping("/proxy")
public class ProxyController {
    @Autowired
    private ProxyService proxyService;

    @RequestMapping("/start")
    public DeferredResult proxySpider(){
        DeferredResult deferredResult = new DeferredResult();
        ThreadFactory namedThreadFactory=new ThreadFactoryBuilder().setNameFormat("proxy-pool-%d").build();
        ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(2,namedThreadFactory);
        ProxyDlTask proxyDlTask=new ProxyDlTask(proxyService);
        proxyDlTask.setProxyEnum(ProxyEnum.XICIDAILI);
        executorService.scheduleAtFixedRate(proxyDlTask, 0, 1, TimeUnit.DAYS);
        proxyDlTask=new ProxyDlTask(proxyService);
        proxyDlTask.setProxyEnum(ProxyEnum.YUNDAILI);
        executorService.scheduleAtFixedRate(proxyDlTask, 0, 1, TimeUnit.DAYS);
        return deferredResult;
    }
}
