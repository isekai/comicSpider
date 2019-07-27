package com.comicspider.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author doctor
 * @Date 19-4-23 下午6:36
 **/
@Entity
@Table(name = "comicspider_comic_tag")
@Data
public class ComicTag {
    /**
     * 漫画标签关系id
     */
    @Id
    private int comicTagId;

    /**
     * 漫画id
     */
    private int comicId;

    /**
     * 标签id
     */
    private int tagId;

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

    public ComicTag(){}

    public ComicTag(int comicId, int tagId) {
        this.comicId = comicId;
        this.tagId = tagId;
    }
}
