package com.comicspider.service;

import com.comicspider.entity.Chapter;

import java.util.List;
import java.util.Map;

/**
 * @Author doctor
 * @Date 19-5-8
 **/
public interface ChapterService extends CurdService<Chapter> {
    void saveKeyVaule(Map<String,Chapter> chapters);
    Chapter findByChapterName(String chapterName);
    List<Chapter> findAllDownloaded(int comicId);
    List<Chapter> findAllNotDownloaded(int comicId);
}
