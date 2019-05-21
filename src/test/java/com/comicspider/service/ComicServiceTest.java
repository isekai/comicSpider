package com.comicspider.service;

import com.comicspider.entity.Comic;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author doctor
 * @Date 19-5-8
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ComicServiceTest {
    @Autowired
    private ComicService comicService;

    @Test
    public void findByComicName(){
        Comic comic=comicService.findByComicName("妄想学生会");
        Assert.assertNotNull(comic);
    }

    @Test
    public void findById(){
        Comic comic=comicService.findById(1);
        Assert.assertNotNull(comic);
    }
}
