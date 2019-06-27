package com.comicspider.controller;

import com.comicspider.entity.Chapter;
import com.comicspider.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-6-27
 **/
@RestController
@RequestMapping("/chapter")
public class ChapterContorller {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping({"","/"})
    public Chapter findById(int chapterId){
        return chapterService.findById(chapterId);
    }

    @RequestMapping("/all")
    public List<Chapter> findByComicId(int comicId){
        return chapterService.findAllByComicId(comicId);
    }
}
