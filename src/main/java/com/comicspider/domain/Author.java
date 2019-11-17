package com.comicspider.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 作者实体
 *
 * @author doctor
 * @date 2019/10/27
 **/
@Entity
@Table(name = "comicspider_author")
@Data
public class Author extends AbstractEntity {

    @JsonIgnore
    private String id;
    private String authorName;
    private String authorAlias;
    private String authorCountry;
    private String socialAccount;

}
