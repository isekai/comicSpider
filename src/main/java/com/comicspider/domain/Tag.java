package com.comicspider.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 标签实体
 *
 * @author doctor
 * @date 2019/10/27
 **/
@Entity
@Table(name = "comicspider_tag")
@Data
public class Tag extends AbstractEntity {

    private String tagId;
    private String tagName;

}
