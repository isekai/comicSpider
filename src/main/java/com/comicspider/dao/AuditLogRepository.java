package com.comicspider.dao;

import com.comicspider.domain.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author doctor
 * @date 2019/10/29
 **/
public interface AuditLogRepository extends JpaRepository<AuditLog, String> {
}
