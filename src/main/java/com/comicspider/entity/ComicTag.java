package com.comicspider.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private int comicTagID;

    /**
     * 漫画id
     */
    private int comicId;

    /**
     * 标签id
     */
    private int tagId;

    public ComicTag(int comicId, int tagId) {
        this.comicId = comicId;
        this.tagId = tagId;
    }
}
