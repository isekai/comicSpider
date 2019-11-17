package com.comicspider.dao;

import com.comicspider.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-4-23 下午8:51
 **/
public interface TagRepository extends JpaRepository<Tag, String> {

    @Query(value = "select t from Tag t " +
            "where t.id in " +
            "(select tag_id from comicspider_comic_tag where comic_id = :comicId)")
    List<Tag> findAllTagName(String comicId);
}
