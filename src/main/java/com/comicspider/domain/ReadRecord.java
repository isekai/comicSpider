package com.comicspider.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 阅读记录实体
 *
 * @author doctor
 * @date 2019/10/27
 **/
@Entity
@Table(name = "comicspider_read_record")
@Data
public class ReadRecord extends AbstractEntity {

    private String id;
    private String userId;
    private String chapterId;
    private int lastNum;
}
