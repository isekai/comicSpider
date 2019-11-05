package com.comicspider.domain;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * 实体抽象类
 *
 * @author doctor
 * @date 2019/10/27
 **/
@Data
public abstract class AbstractEntity {

    @Column(insertable = false, updatable = false)
    private Date createTime;
    @Column(insertable = false, updatable = false)
    private Date updateTime;

}
