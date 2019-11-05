package com.comicspider.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 收藏记录实体
 *
 * @author doctor
 * @date 2019/10/27
 **/
@Entity
@Table(name = "comicspider_collection_record")
@Data
public class CollectionRecord extends AbstractEntity {

    private String id;
    private String userId;
    private String comicId;

}
