package com.comicspider.controller;

import com.comicspider.cartoonmad.downloader.ComicDlTask;
import com.comicspider.cartoonmad.downloader.FileDlTask;
import com.comicspider.config.GlobalConfig;
import com.comicspider.entity.Chapter;
import com.comicspider.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

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

    @RequestMapping("/start")
    public DeferredResult start(){
        DeferredResult result=new DeferredResult();
        ComicDlTask comicDlTask=new ComicDlTask(comicService,chapterService,tagService,comicTagService,redisService);
        return result;
    }

    @RequestMapping("/test")
    public DeferredResult zipIoTest(){
        DeferredResult result=new DeferredResult();
        Chapter chapter=new Chapter(25,"第001话");
        FileDlTask fileDlTask=new FileDlTask();
        fileDlTask.setBaseUrl("https://www.cartoonmad.com/comic/749100012025001");
        fileDlTask.setChapter(chapter);
        fileDlTask.setProxy(null);
        fileDlTask.setBasePath(GlobalConfig.ROOT_PATH);
        ExecutorService executorService=new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));
        executorService.execute(fileDlTask);
        executorService.shutdown();
        return result;
    }

}
