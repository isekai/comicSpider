package com.comicspider.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author doctor
 * @Date 19-4-23 下午5:55
 **/
@Entity
@Table(name = "comicspider_tag")
@Data
public class Tag {
    /**
     * 标签id
     */
    @Id
    private int tagId;

    /**
     * 标签名
     */
    private String tagName;

    public Tag(){}

    public Tag(String tagName) {
        this.tagName = tagName;
    }
}
