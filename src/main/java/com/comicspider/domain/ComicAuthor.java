package com.comicspider.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 漫画作者关联实体
 *
 * @author doctor
 * @date 2019/10/27
 **/
@Entity
@Table(name = "comicspider_comic_author")
@Data
public class ComicAuthor extends AbstractEntity {

    private String id;
    private String comicId;
    private String authorId;

}
