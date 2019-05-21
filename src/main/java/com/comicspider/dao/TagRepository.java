package com.comicspider.dao;

import com.comicspider.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author doctor
 * @Date 19-4-23 下午8:51
 **/
public interface TagRepository extends JpaRepository<Tag,Integer> {
}
