package com.comicspider.dao;

import com.comicspider.domain.Proxy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author doctor
 * @Date 19-4-25 下午4:31
 **/
public interface ProxyRepository extends JpaRepository<Proxy, String> {
}
