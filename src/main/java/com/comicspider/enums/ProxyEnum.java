package com.comicspider.enums;

import lombok.Getter;

/**
 * @Author doctor
 * @Date 19-6-29
 **/
@Getter
public enum  ProxyEnum {
    /**
     * 爬取代理ip的目标网址，0是西刺代理，1是ip海
     */
    YUNDAILI(1,"http://www.ip3366.net/free/"),
    XICIDAILI(0,"https://www.xicidaili.com/nn/");

    private int code;
    private String url;

    ProxyEnum(int code,String url){
        this.code=code;
        this.url=url;
    }
}
