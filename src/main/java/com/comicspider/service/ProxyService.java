package com.comicspider.service;

import com.comicspider.entity.Proxy;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-5-8
 **/
public interface ProxyService extends CurdService<Proxy> {
    List<Proxy> findAllByIp(String ip);
}

