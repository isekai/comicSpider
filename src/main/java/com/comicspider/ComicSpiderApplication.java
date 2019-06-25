package com.comicspider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @Author doctor
 * @Date 19-5-1 上午10:55
 **/
@SpringBootApplication
public class ComicSpiderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComicSpiderApplication.class, args);
    }
}
