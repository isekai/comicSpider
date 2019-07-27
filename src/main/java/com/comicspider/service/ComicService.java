package com.comicspider.service;

import com.comicspider.entity.Comic;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-5-1 上午10:35
 **/
public interface ComicService extends CurdService<Comic> {

    Comic findByComicName(String comicName);

    List<Comic> findAllEnded();

    List<Comic> findAllNotEnded();

    List<Comic> findAllByCategory(String category);

    List<Comic> findAllByAuthor(String author);

    List<Comic> findAllByPage(int pageNum,int pageSize);
}
