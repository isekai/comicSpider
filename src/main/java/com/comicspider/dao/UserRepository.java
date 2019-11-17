package com.comicspider.dao;

import com.comicspider.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author doctor
 * @date 2019/10/29
 **/
public interface UserRepository extends JpaRepository<User, String> {
}
