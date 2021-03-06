package com.comicspider.service.impl;

import com.comicspider.dao.ChapterRepository;
import com.comicspider.entity.Chapter;
import com.comicspider.enums.DownloadedEnum;
import com.comicspider.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-5-8
 **/
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public Chapter findByChapterName(String chapterName) {
        return chapterRepository.findByChapterName(chapterName);
    }

    @Override
    public List<Chapter> findAllByComicId(int comicId) {
        return chapterRepository.findAllByComicId(comicId);
    }

/*    @Override
    public List<Chapter> findAllDownloaded(int comicId) {
        return chapterRepository.findAllByComicIdAndDownloaded(comicId,DownloadedEnum.DOWNLOADED.getCode());
    }

    @Override
    public List<Chapter> findAllNotDownloaded(int comicId) {
        return chapterRepository.findAllByComicIdAndDownloaded(comicId,DownloadedEnum.UN_DOWNLOADED.getCode());
    }*/

    @Override
    public Chapter findById(int id) {
        return chapterRepository.findById(id).orElse(null);
    }

    @Override
    public void saveOrUpdate(Chapter chapter) {
        chapterRepository.save(chapter);
    }

    @Override
    public void deleteById(int id) {
        chapterRepository.deleteById(id);
    }
}
