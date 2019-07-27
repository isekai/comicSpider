package com.comicspider.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 代表一个网络代理实体
 * @Author doctor
 * @Date 19-1-15 下午1:11
 **/
@Entity
@Table(name = "comicspider_proxy")
@Data
public class Proxy {
    @Id
    private int proxyId;

    /**
     * 代理ip
     */
    private String ip;

    /**
     * 代理端口
     */
    private int port;

    /**
     * 协议类型
     */
    private String type;

    /**
     * 创建时间
     */
    @Column(insertable = false,updatable = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(insertable = false,updatable = false)
    private Date updateTime;

    public Proxy(){}

    public Proxy(String ip, int port, String type) {
        this.ip = ip;
        this.port = port;
        this.type=type;
    }
}

