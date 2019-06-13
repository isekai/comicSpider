package com.comicspider.enums;

import lombok.Getter;

/**
 * @Author doctor
 * @Date 19-6-13
 **/
@Getter
public enum  EndEnum {
    /**
     * 是否已完结,0代表未完结，1代表已完结
     */
    END(1,"已完结"),
    UN_END(0,"未完结");

    private int code;
    private String message;

    EndEnum(int code,String message){
        this.code=code;
        this.message=message;
    }
}
