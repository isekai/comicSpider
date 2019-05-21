package com.comicspider.dao;

import com.comicspider.entity.Comic;
import com.comicspider.enums.DownloadedEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author doctor
 * @Date 19-5-1 上午10:45
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ComicRepositoryTest {

    @Autowired
    private ComicRepository comicRepository;

    @Test
    public void save(){
        Comic comic=new Comic();
        comic.setComicName("妄想学生会");
        comic.setAuthor("氏家卜全");
        comic.setCategory("少年漫画");
        comic.setDownloaded(DownloadedEnum.UN_DOWNLOADED.getCode());
        comicRepository.save(comic);
    }

    @Test
    public void findByComicName(){
        Comic comic=comicRepository.findByComicName("天降之物");
        assertTrue(comic == null);
    }
}