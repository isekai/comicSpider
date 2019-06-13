package com.comicspider.dao;

import com.comicspider.entity.Proxy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-4-25 下午4:31
 **/
public interface ProxyRepository extends JpaRepository<Proxy,Integer>  {
    Proxy findByIp(String ip);
}
