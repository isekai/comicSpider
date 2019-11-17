package com.comicspider.dao;

import com.comicspider.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author doctor
 * @date 2019/10/29
 **/
public interface CommentRepository extends JpaRepository<Comment,String> {
}
