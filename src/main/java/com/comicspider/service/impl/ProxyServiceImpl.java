package com.comicspider.service.impl;

import com.comicspider.dao.ProxyRepository;
import com.comicspider.entity.Proxy;
import com.comicspider.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-5-8
 **/
@Service
public class ProxyServiceImpl implements ProxyService {
    @Autowired
    private ProxyRepository proxyRepository;

    @Override
    public Proxy findById(int id) {
        return proxyRepository.findById(id).orElse(null);
    }

    @Override
    public void saveOrUpdate(Proxy proxy) {
        proxyRepository.save(proxy);
    }

    @Override
    public void deleteById(int id) {
        proxyRepository.deleteById(id);
    }

    @Override
    public Proxy findByIp(String ip) {
        return proxyRepository.findByIp(ip);
    }

    @Override
    public List<Proxy> findAll() {
        return proxyRepository.findAll();
    }
}
