package com.comicspider.exception;

import com.comicspider.enums.ExceptionEnum;
import lombok.Getter;

/** 全局异常
 * @Author doctor
 * @Date 19-5-23
 **/
public class SpiderException extends RuntimeException {
    @Getter
    private int code;
    @Getter
    private String msg;

    public SpiderException(ExceptionEnum e) {
        this.code = e.getCode();
        this.msg = e.getMsg();
    }

    public SpiderException(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
