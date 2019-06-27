package com.comicspider.dao;

import com.comicspider.entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-4-22 下午9:56
 **/
public interface ComicRepository extends JpaRepository<Comic,Integer> {
    Comic findByComicName(String comicName);
    List<Comic> findAllByEnd(int end);
    List<Comic> findAllByDownloaded(int downloaded);
    List<Comic> findAllByCategory(String category);
    List<Comic> findAllByAuthor(String author);
}
