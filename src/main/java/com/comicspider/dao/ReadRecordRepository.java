package com.comicspider.dao;

import com.comicspider.domain.ReadRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author doctor
 * @date 2019/10/29
 **/
public interface ReadRecordRepository extends JpaRepository<ReadRecord,String> {
}
