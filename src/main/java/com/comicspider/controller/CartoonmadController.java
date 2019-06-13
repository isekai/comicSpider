package com.comicspider.controller;

import com.comicspider.cartoonmad.downloader.ComicDlTask;
import com.comicspider.cartoonmad.downloader.FileDlTask;
import com.comicspider.config.GlobalConfig;
import com.comicspider.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        ComicDlTask comicDlTask=new ComicDlTask(comicService,chapterService,tagService,comicTagService,redisService);
        ExecutorService executorService=new ThreadPoolExecutor(GlobalConfig.POOL_SIZE, GlobalConfig.POOL_SIZE, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(GlobalConfig.POOL_SIZE));
        executorService.execute(comicDlTask);
        executorService.shutdown();
        return result;
    }

    @RequestMapping("/test")
    public DeferredResult zipIoTest(){
        DeferredResult result=new DeferredResult();
/*        FileDlTask fileDlTask=new FileDlTask();
        redisService.get();
        fileDlTask.setBaseUrl("https://www.cartoonmad.com/comic/749100012025001");
        fileDlTask.setChapter(chapter);
        fileDlTask.setProxy(null);
        fileDlTask.setBasePath(GlobalConfig.ROOT_PATH);
        ExecutorService executorService=new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));
        executorService.execute(fileDlTask);
        executorService.shutdown();*/
        return result;
    }

/*    private List<Proxy> getProxi(){
        redisService.keys();
    }
    */


}
