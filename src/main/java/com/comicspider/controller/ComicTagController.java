package com.comicspider.controller;

import com.comicspider.entity.Comic;
import com.comicspider.entity.ComicTag;
import com.comicspider.entity.Tag;
import com.comicspider.service.ComicService;
import com.comicspider.service.ComicTagService;
import com.comicspider.service.TagService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author doctor
 * @Date 19-6-27
 **/
@RestController
@RequestMapping("/comictag")
public class ComicTagController {
    @Autowired
    private ComicTagService comicTagService;
    @Autowired
    private ComicService comicService;
    @Autowired
    private TagService tagService;

    @RequestMapping("/comiclist")
    public List<Comic> findByTag(int tagId){
        List<Comic> comicList=new ArrayList<>();
        List<ComicTag> comicTagList=comicTagService.findAllByTagId(tagId);
        for (ComicTag comicTag:comicTagList){
            comicList.add(comicService.findById(comicTag.getComicId()));
        }
        return comicList;
    }

    @RequestMapping("/taglist")
    public List<Tag> findByComic(int comicId){
        List<Tag> tagList=new ArrayList<>();
        List<ComicTag> comicTagList=comicTagService.findAllByComicId(comicId);
        for (ComicTag comicTag:comicTagList){
            tagList.add(tagService.findById(comicTag.getTagId()));
        }
        return tagList;
    }

}
