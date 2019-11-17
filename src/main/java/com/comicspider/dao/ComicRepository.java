package com.comicspider.dao;

import com.comicspider.domain.Comic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author doctor
 * @Date 19-4-22 下午9:56
 **/
public interface ComicRepository extends JpaRepository<Comic, String> {
}
