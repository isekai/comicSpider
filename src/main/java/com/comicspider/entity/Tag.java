package com.comicspider.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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

    /**
     * 创建时间
     */
    @Column(insertable = false,updatable = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(insertable = false,updatable = false)
    private Date updateTime;

    public Tag(){}

    public Tag(String tagName) {
        this.tagName = tagName;
    }
}
