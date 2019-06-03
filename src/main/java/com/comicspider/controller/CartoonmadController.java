package com.comicspider.controller;

import com.comicspider.cartoonmad.downloader.ComicDlTask;
import com.comicspider.service.ChapterService;
import com.comicspider.service.ComicService;
import com.comicspider.service.ComicTagService;
import com.comicspider.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

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

    @RequestMapping("/start")
    public DeferredResult start(){
        DeferredResult result=new DeferredResult();
        ComicDlTask comicDlTask=new ComicDlTask(comicService,chapterService,tagService,comicTagService);
        return result;
    }

}
