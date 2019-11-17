package com.comicspider.dao;

import com.comicspider.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author doctor
 * @date 2019/10/29
 **/
public interface AuthorRepository extends JpaRepository<Author, String> {

    @Query(value = "select a " +
            "from Author a where a.id in " +
            "(select author_id from comicspider_comic_author where comic_id = :comicId)")
    List<Author> findAllByComic(String comicId);
}
