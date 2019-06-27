package com.comicspider.dao;

import com.comicspider.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-4-25 下午4:42
 **/
public interface ChapterRepository extends JpaRepository<Chapter,Integer> {
    Chapter findByChapterName(String chapterName);
    List<Chapter> findAllByComicId(int comicId);
/*    List<Chapter> findAllByComicIdAndDownloaded(int comicId,int downloaded);*/
}
