package com.comicspider.dao;

import com.comicspider.domain.CollectionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author doctor
 * @date 2019/10/29
 **/
public interface CollectionRecordRepository extends JpaRepository<CollectionRecord, String> {
}
