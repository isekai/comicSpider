package com.comicspider.dao;

import com.comicspider.domain.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-4-25 下午4:42
 **/
public interface ChapterRepository extends JpaRepository<Chapter, String> {

    List<Chapter> findAllByComicId(String comicId);

    Chapter findFirstByComicIdOrderByUpdateTimeDesc(String comicId);
}
