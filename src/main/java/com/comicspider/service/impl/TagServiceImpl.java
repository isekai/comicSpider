package com.comicspider.service.impl;

import com.comicspider.dao.TagRepository;
import com.comicspider.entity.Tag;
import com.comicspider.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author doctor
 * @Date 19-5-8
 **/
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag findById(int id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public void saveOrUpdate(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void deleteById(int id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag findByTagName(String tagName) {
        return tagRepository.findByTagName(tagName);
    }
}
