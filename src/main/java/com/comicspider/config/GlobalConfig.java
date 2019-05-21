package com.comicspider.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Author doctor
 * @Date 19-5-15
 **/
public class GlobalConfig {
    public static String USER_AGENT;

    @Value("${comicSpider.user-agent}")
    public void setUserAgent(String userAgent) {
        USER_AGENT = userAgent;
    }

    public static String PROXY_URL;

}
