package com.comicspider.service.impl;

import com.comicspider.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author doctor
 * @Date 19-6-7
 **/
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void set(String key, Object o) {
        redisTemplate.opsForValue().set(key, o);

    }

    @Override
    public void leftPush(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public void leftPushAll(String key, Collection<Object> collection) {
        redisTemplate.opsForList().leftPushAll(key, collection);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);

    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public String randomKey() {
        return redisTemplate.randomKey();
    }


    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Object leftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }


}
