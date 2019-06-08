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
    public void delete(String key) {
        redisTemplate.delete(key);

    }

    @Override
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public List<Object> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }
}
