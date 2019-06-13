package com.comicspider.enums;
import lombok.Getter;

/**
 * 下载状态的枚举类
 * @Author doctor
 * @Date 19-5-1 上午10:19
 **/

@Getter
public enum DownloadedEnum {
    /**
     * 是否下载,0代表未下载，1代表已下载
     */
    DOWNLOADED(1,"已下载"),
    UN_DOWNLOADED(0,"未下载");
    
    private int code;
    private String message;
    
    DownloadedEnum(int code,String message){
        this.code=code;
        this.message=message;
    }
}
