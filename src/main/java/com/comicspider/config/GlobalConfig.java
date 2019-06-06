package com.comicspider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 全局配置类01
 * @Author doctor
 * @Date 19-5-15
 **/
@Configuration
public class GlobalConfig {
    public static String USER_AGENT;

    @Value("${comicSpider.user-agent}")
    public void setUserAgent(String userAgent) {
        USER_AGENT = userAgent;
    }

    public static String ROOT_PATH;

    @Value("${comicSpider.root-path}")
    public void setRootPath(String rootPath){
        ROOT_PATH=rootPath;
    }

    public static String CARTOONMAD_URL;

    @Value("${comicSpider.cartoonmad-url}")
    public void setCartoonmadUrl(String cartoonmadUrl){
        CARTOONMAD_URL=cartoonmadUrl;
    }

    public static String PROXY_TEST_URL;

    @Value("${comicSpider.proxy-test-url}")
    public void setProxyTestUrl(String proxyTestUrl){
        PROXY_TEST_URL=proxyTestUrl;
    }

}
