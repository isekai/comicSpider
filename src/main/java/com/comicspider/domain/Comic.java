package com.comicspider.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 漫画实体
 *
 * @author doctor
 * @date 2019/10/27
 **/
@Entity
@Table(name = "comicspider_comic")
@Data
public class Comic extends AbstractEntity {

    private String id;
    private String category;
    private String comicName;
    private String aliasName;
    private String realName;
    private String comicType;
    private String language;
    private String country;
    private String publish;
    private String cover;
    private String description;
    private int clickNum;
    private int status;

}
