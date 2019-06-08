package com.comicspider.service;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;

/**
 * @Author doctor
 * @Date 19-6-6
 **/
public interface RedisService {
    void set(String key,Object o);
    void delete(String key);
    void delete(Collection<String> keys);
    Object get(String key);
    List<Object>  multiGet(Collection<String> keys);
}
