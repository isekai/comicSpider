package com.comicspider.redis;

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
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void addUrl(){
        redisTemplate.opsForValue().set("1","baidu.com");
        Assert.assertEquals("baidu.com",redisTemplate.opsForValue().get("1"));
    }
}
