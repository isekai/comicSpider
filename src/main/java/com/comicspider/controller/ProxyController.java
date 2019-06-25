package com.comicspider.controller;

import com.comicspider.cartoonmad.downloader.ProxyDlTask;
import com.comicspider.service.ProxyService;
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

    @RequestMapping("/proxySpider")
    public DeferredResult proxySpider(){
        DeferredResult deferredResult = new DeferredResult();
        ProxyDlTask proxyDlTask;
        proxyDlTask=new ProxyDlTask(proxyService);
        proxyDlTask.setUrl("https://www.xicidaili.com/nn/");
        ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(1);
        executorService.scheduleAtFixedRate(proxyDlTask, 0, 1, TimeUnit.DAYS);
        return deferredResult;
    }
}
