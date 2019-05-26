package com.comicspider.enums;

import lombok.Getter;

/**
 * @Author doctor
 * @Date 19-5-23
 **/

public enum  ExceptionEnum {
    NOT_DOWNLOAD(9999,"下载失败");

    @Getter
    private int code;
    @Getter
    private String msg;

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
