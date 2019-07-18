package com.comicspider.enums;

import lombok.Getter;

/**
 * @Author doctor
 * @Date 19-5-23
 **/

public enum  ExceptionEnum {
    /**
     * 可能产生的异常枚举
    **/
    NOT_DOWNLOAD(9999,"下载失败"),
    CONNECT_TIMEOUT(500,"连接超时"),
    FILE_NOT_FOUND(404,"找不到资源");

    @Getter
    private int code;
    @Getter
    private String msg;

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
