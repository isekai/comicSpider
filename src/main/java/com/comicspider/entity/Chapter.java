package com.comicspider.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表示漫画中的一个章节
 * @Author doctor
 * @Date 19-1-15 下午12:55
 **/
@Entity
@Table(name = "comicspider_chapter")
@Data
public class Chapter {
    @Id
    private int chapterId;

    private int comicId;

    /**
     * 是否已下载
     */
    private int downloaded;

    /**
     * 章节页数
     */
    private int pageNum;

    /**
     * 章节名
     */
    private String chapterName;


}
