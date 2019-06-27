package com.comicspider.controller;

import com.comicspider.cartoonmad.downloader.ComicDlTask;
import com.comicspider.cartoonmad.downloader.FileDlTask;
import com.comicspider.config.GlobalConfig;
import com.comicspider.entity.Proxy;
import com.comicspider.service.*;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Author doctor
 * @Date 19-6-3
 **/
@RestController
@RequestMapping("/cartoonmad")
@Slf4j
public class CartoonmadController {
    @Autowired
    private ComicService comicService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ComicTagService comicTagService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ProxyService proxyService;

    @RequestMapping("/start")
    public DeferredResult start(){
        DeferredResult result=new DeferredResult();
        List<Proxy> proxies=proxyService.findAll();
        int startId=GlobalConfig.START_ID;
        int downloadNum=10;
        ThreadFactory namedThreadFactory=new ThreadFactoryBuilder().setNameFormat("cartoonmadSpider-pool-%d").build();
        ExecutorService executorService=new ThreadPoolExecutor(1, GlobalConfig.POOL_SIZE, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024),namedThreadFactory);
        for (int i=0;i<10;i++){
            int[] comicIds=new int[downloadNum];
            for (int j=0;j<downloadNum;j++){
                comicIds[j]=startId+j;
            }
            ComicDlTask comicDlTask=new ComicDlTask(comicService,chapterService,tagService,comicTagService,redisService);
            comicDlTask.setComicIds(comicIds);
            comicDlTask.setProxy(proxies .get((int)(Math.random()*proxies.size())));
            executorService.execute(comicDlTask);
            startId=startId+downloadNum;
        }
        executorService.shutdown();
        return result;
    }

    @RequestMapping("/download")
    public DeferredResult zipIoTest(){
        DeferredResult result=new DeferredResult();
        FileDlTask fileDlTask=new FileDlTask(comicService,redisService,chapterService);
        ExecutorService executorService=new ThreadPoolExecutor(GlobalConfig.POOL_SIZE, GlobalConfig.POOL_SIZE+3, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024));
        fileDlTask.setProxies(proxyService.findAll());
        executorService.execute(fileDlTask);
        executorService.shutdown();
        return result;
    }



}
