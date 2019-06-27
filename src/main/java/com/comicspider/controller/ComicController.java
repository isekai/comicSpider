package com.comicspider.controller;

import com.comicspider.entity.Comic;
import com.comicspider.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-6-27
 **/
@RestController
@RequestMapping("/comic")
public class ComicController {
    @Autowired
    private ComicService comicService;

    @RequestMapping("/index")
    public Comic findByComicId(int comicId){
        return comicService.findById(comicId);
    }

    @RequestMapping({"","/"})
    public List<Comic> findByPage(int pageNum,int pageSize){
        return comicService.findAllByPage(pageNum, pageSize);
    }

    @RequestMapping("/author")
    public List<Comic> findByAuthor(String author){
        return comicService.findAllByAuthor(author);
    }

    @RequestMapping("/category")
    public List<Comic> findByCategory(String category){
        return comicService.findAllByCategory(category);
    }

    @RequestMapping("/downloaded")
    public List<Comic> findAllDownloaded(){
        return comicService.findAllDownloaded();
    }

    @RequestMapping("/undownloaded")
    public List<Comic> findAllNotDownloaded(){
        return comicService.findAllNotDownloaded();
    }

    @RequestMapping("/ended")
    public List<Comic> findAllEnded(){
        return comicService.findAllEnded();
    }

    @RequestMapping("/unended")
    public List<Comic> findAllNotEnded(){
        return comicService.findAllNotEnded();
    }

}
