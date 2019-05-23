package com.comicspider.exception;

import lombok.Getter;

/** 全局异常
 * @Author doctor
 * @Date 19-5-23
 **/
public class GlobalException extends RuntimeException {
    @Getter
    private int code;
    @Getter
    private String msg;

    public GlobalException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
