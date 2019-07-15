package com.comicspider.service;

import com.comicspider.entity.Proxy;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @Author doctor
 * @Date 19-5-8
 **/
public interface ProxyService extends CurdService<Proxy> {
    void saveAll(List<Proxy> proxies);
    void deleteAll();
    Proxy findByIp(String ip);
    List<Proxy> findAll();
}

