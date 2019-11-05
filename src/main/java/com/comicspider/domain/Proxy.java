package com.comicspider.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ip代理实体
 *
 * @author doctor
 * @date 2019/10/27
 **/
@Entity
@Table(name = "comicspider_proxy")
@Data
public class Proxy extends AbstractEntity {

    private String id;
    private String ip;
    private int port;
    private String type;
    private int alive;

}
