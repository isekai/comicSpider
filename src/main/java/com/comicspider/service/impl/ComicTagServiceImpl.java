package com.comicspider.service.impl;

import com.comicspider.dao.ComicTagRepository;
import com.comicspider.entity.ComicTag;
import com.comicspider.service.ComicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-5-9
 **/
@Service
public class ComicTagServiceImpl implements ComicTagService {
    @Autowired
    private ComicTagRepository comicTagRepository;

    @Override
    public List<ComicTag> findAllByTagId(int tagId) {
        return comicTagRepository.findAllByTagId(tagId);
    }

    @Override
    public List<ComicTag> findAllByComicId(int comicId) {
        return comicTagRepository.findAllByComicId(comicId);
    }

    @Override
    public void deleteAllByComicIdOrTagId(int comicId, int tagId) {
        comicTagRepository.deleteAllByComicIdOrTagId(comicId,tagId);
    }

    @Override
    public ComicTag findById(int id) {
        return comicTagRepository.findById(id).orElse(null);
    }

    @Override
    public void saveOrUpdate(ComicTag comicTag) {
        comicTagRepository.save(comicTag);
    }

    @Override
    public void deleteById(int id) {
        comicTagRepository.deleteById(id);
    }
}
