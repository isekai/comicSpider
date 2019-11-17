package com.comicspider.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 章节实体
 *
 * @author doctor
 * @date 2019/10/27
 **/
@Entity
@Table(name = "comicspider_chapter")
@Data
public class Chapter extends AbstractEntity {

    @JsonIgnore
    private String id;
    @JsonIgnore
    private String comicId;
    private String chapterName;
    private int chapterNum;
    private int pageSum;
    private int chapterType;

}
