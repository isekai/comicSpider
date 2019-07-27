package com.comicspider.service.impl;

import com.comicspider.dao.ComicRepository;
import com.comicspider.entity.Comic;
import com.comicspider.enums.EndEnum;
import com.comicspider.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author doctor
 * @Date 19-5-1 上午10:38
 **/
@Service
public class ComicServiceImpl implements ComicService {

    @Autowired
    private ComicRepository comicRepository;

    @Override
    public Comic findByComicName(String comicName) {
        return comicRepository.findByComicName(comicName);
    }

    @Override
    public List<Comic> findAllEnded() {
        return comicRepository.findAllByEnd(EndEnum.END.getCode());
    }

    @Override
    public List<Comic> findAllNotEnded() {
        return comicRepository.findAllByEnd(EndEnum.UN_END.getCode());
    }


    @Override
    public List<Comic> findAllByCategory(String category) {
        return comicRepository.findAllByCategory(category);
    }

    @Override
    public List<Comic> findAllByAuthor(String author) {
        return comicRepository.findAllByAuthor(author);
    }

    @Override
    public List<Comic> findAllByPage(int pageNum, int pageSize) {
        Pageable pageable= PageRequest.of(pageNum, pageSize,new Sort(Sort.Direction.DESC,"updateTime"));
        return comicRepository.findAll(pageable).getContent();
    }

    @Override
    public Comic findById(int id) {
        return comicRepository.findById(id).orElse(null);
    }

    @Override
    public void saveOrUpdate(Comic comic) {
        comicRepository.save(comic);
    }

    @Override
    public void deleteById(int id) {
        comicRepository.deleteById(id);
    }

}
