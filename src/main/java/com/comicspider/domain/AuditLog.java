package com.comicspider.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 日志实体
 *
 * @author doctor
 * @date 2019/10/27
 **/
@Entity
@Table(name = "audit_log")
@Data
public class AuditLog extends AbstractEntity {

    private String id;
    private String content;
    private String logType;
    private int level;

}
