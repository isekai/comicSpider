package com.comicspider.service;

import com.comicspider.entity.Tag;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-5-8
 **/
public interface TagService extends CurdService<Tag> {
    Tag findByTagName(String tagName);
    List<Tag> findAll();
}
