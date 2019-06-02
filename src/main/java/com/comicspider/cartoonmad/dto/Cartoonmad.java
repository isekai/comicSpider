package com.comicspider.cartoonmad.dto;

import com.comicspider.entity.Chapter;
import com.comicspider.entity.Comic;
import com.comicspider.entity.Tag;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 对cartoonmad的漫画页面抽象出实体类
 * @Author doctor
 * @Date 19-5-28
 **/
@Data
public class Cartoonmad {
    private Comic comic;
    private List<Tag> tags;
    private Map<String,Chapter> chapters;

    public Cartoonmad(Comic comic, List<Tag> tags, Map<String,Chapter> chapters) {
        this.comic = comic;
        this.tags = tags;
        this.chapters = chapters;
    }
}
