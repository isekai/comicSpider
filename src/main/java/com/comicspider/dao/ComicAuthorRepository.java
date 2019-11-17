package com.comicspider.dao;

import com.comicspider.domain.ComicAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author doctor
 * @date 2019/10/29
 **/
public interface ComicAuthorRepository extends JpaRepository<ComicAuthor,String> {
}
