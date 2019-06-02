package com.comicspider.dao;

import com.comicspider.entity.ComicTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-4-25 下午5:12
 **/
public interface ComicTagRepository extends JpaRepository<ComicTag,Integer> {
    ComicTag findByComicIdAndTagId(int comicId,int tagId);
    List<ComicTag> findAllByTagId(int tagId);
    List<ComicTag> findAllByComicId(int comicId);
    void deleteAllByComicIdOrTagId(int comicId,int tagId);
}
