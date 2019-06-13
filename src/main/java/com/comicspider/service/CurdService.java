package com.comicspider.service;

/**
 * @Author doctor
 * @Date 19-5-1 上午10:32
 **/
public interface CurdService<T> {
    T findById(int id);

    void saveOrUpdate(T t);

    void deleteById(int id);
}
