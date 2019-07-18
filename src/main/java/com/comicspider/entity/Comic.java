package com.comicspider.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 表示一本漫画实体
 * @Author doctor
 * @Date 19-1-15 下午12:50
 **/
@Entity
@Table(name = "comicspider_comic")
@Data
public class Comic {
    @Id
    private int comicId;

    /**
     * 是否已下载
     */
    private int downloaded;

    /**
     * 是否已完结
     */
    private int end;

    /**
     * 章节数目
     */
    private int chapterNum;

    /**
     * 漫画名
     */
    private String comicName;

    /**
     * 封面（base64）
     */
    private String cover;

    /**
     * 分类
     */
    private String category;

    /**
     * 作者
     */
    private String author;

    /**
     * 漫画简介
     */
    private String about;

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

    public Comic(){}
}
