package com.comicspider.redis;

import com.comicspider.entity.Chapter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author doctor
 * @Date 19-5-23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void addUrl(){
        Chapter chapter=new Chapter();
        chapter.setChapterName("第x话");
        chapter.setPageNum(19);
        redisTemplate.opsForValue().set("1",chapter);
        Assert.assertEquals(chapter,redisTemplate.opsForValue().get("1"));
    }
}
