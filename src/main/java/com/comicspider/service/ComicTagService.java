package com.comicspider.service;

import com.comicspider.entity.ComicTag;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-5-8
 **/
public interface ComicTagService extends CurdService<ComicTag> {
    ComicTag findByComicIdAndTagId(int comicId, int tagId);
    List<ComicTag> findAllByTagId(int tagId);
    List<ComicTag> findAllByComicId(int comicId);
    void deleteAllByComicIdOrTagId(int comicId,int tagId);
}
