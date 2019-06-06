package com.comicspider.dto;

import lombok.Getter;

/**
 * @Author doctor
 * @Date 19-6-4
 **/
public class ResponseCode {
    @Getter
    private int code;
    @Getter
    private String msg;
    @Getter
    private Object data;

    public ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseCode(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
