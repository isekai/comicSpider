package com.comicspider.dao;

import com.comicspider.domain.ComicTag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author doctor
 * @Date 19-4-25 下午5:12
 **/
public interface ComicTagRepository extends JpaRepository<ComicTag, String> {
}
