package com.comicspider.controller;

import com.comicspider.entity.Tag;
import com.comicspider.service.ComicTagService;
import com.comicspider.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-6-27
 **/
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping({"","/"})
    public Tag findById(int tagId){
        return tagService.findById(tagId);
    }

    @RequestMapping("all")
    public List<Tag> findAll(){
        return tagService.findAll();
    }

}
