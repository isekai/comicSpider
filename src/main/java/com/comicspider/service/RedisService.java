package com.comicspider.service;


import java.util.Collection;

/**
 * @Author doctor
 * @Date 19-6-6
 **/
public interface RedisService {
    void set(String key,Object o);
    void leftPush(String key,Object value);
    void leftPushAll(String key, Collection<Object> collection);
    void delete(String key);
    boolean hasKey(String key);
    String randomKey();
    Object get(String key);
    Object leftPop(String key);
}
