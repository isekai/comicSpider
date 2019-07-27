package com.comicspider.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表示漫画中的一个章节
 * @Author doctor
 * @Date 19-1-15 下午12:55
 **/
@Entity
@Table(name = "comicspider_chapter")
@Data
public class Chapter implements Serializable {

    private static final long serialVersionUID = -2664466467518055688L;

    @Id
    private int chapterId;

    private int comicId;

    /**
     * 章节页数
     */
    private int pageNum;

    /**
     * 章节类型
     */
    private int chapterType;

    /**
     * 章节名
     */
    private String chapterName;

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

    public Chapter() {
    }


}
