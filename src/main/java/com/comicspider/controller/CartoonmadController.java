package com.comicspider.controller;

import com.comicspider.cartoonmad.downloader.ComicDlTask;
import com.comicspider.cartoonmad.downloader.FileDlTask;
import com.comicspider.config.GlobalConfig;
import com.comicspider.service.*;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
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
    public DeferredResult start(int startId,int downloadNum,int poolSize){
        DeferredResult result=new DeferredResult();
        if (startId==0 || downloadNum==0 || poolSize==0){
            startId=GlobalConfig.START_ID;
            downloadNum=GlobalConfig.DOWNLOAD_NUM;
            poolSize=GlobalConfig.POOL_SIZE;
        }
        ThreadFactory namedThreadFactory=new ThreadFactoryBuilder().setNameFormat("cartoonmadSpider-pool-%d").build();
        ExecutorService executorService=new ThreadPoolExecutor(poolSize, poolSize, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024),namedThreadFactory);
        for (int i=0;i<poolSize;i++){
            int[] comicIds=new int[downloadNum];
            for (int j=0;j<downloadNum;j++){
                comicIds[j]=startId+j;
            }
            ComicDlTask comicDlTask=new ComicDlTask(comicService,chapterService,tagService,comicTagService,redisService);
            comicDlTask.setComicIds(comicIds);
            comicDlTask.setProxies(proxyService.findAll());
            executorService.execute(comicDlTask);
            startId=startId+downloadNum;
        }
        executorService.shutdown();
        return result;
    }

    @RequestMapping("/download")
    public DeferredResult download(int poolSize,String rootPath){
        DeferredResult result=new DeferredResult();
        if (poolSize==0 || rootPath==null){
            poolSize=GlobalConfig.POOL_SIZE;
            rootPath=GlobalConfig.ROOT_PATH;
        }
        ThreadFactory namedThreadFactory=new ThreadFactoryBuilder().setNameFormat("download-pool-%d").build();
        ExecutorService executorService=new ThreadPoolExecutor(poolSize, poolSize, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024),namedThreadFactory);
        for (int i=0;i<poolSize;i++){
            FileDlTask fileDlTask=new FileDlTask(redisService,chapterService);
            fileDlTask.setProxies(proxyService.findAll());
            fileDlTask.setRootPath(rootPath);
            executorService.execute(fileDlTask);
        }
        executorService.shutdown();
        return result;
    }

}
