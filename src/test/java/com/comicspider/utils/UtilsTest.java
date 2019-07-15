package com.comicspider.utils;

import com.comicspider.config.GlobalConfig;
import com.comicspider.controller.CartoonmadController;
import com.comicspider.entity.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author doctor
 * @Date 19-5-23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UtilsTest {
    @Test
    public void test(){
        String jsonStr=new String(HttpUtil.get("http://www.66ip.cn/nmtq.php?getnum=20&isp=0&anonymoustype=0&start=&ports=&export=&ipaddress=&area=1&proxytype=1&api=66ip"), StandardCharsets.UTF_8);
        System.out.println(jsonStr);
    }

    @Test
    public void getComic(){
        Proxy proxy=new Proxy("106.14.160.134",80,"http");
        byte[] comic=HttpUtil.get("https://www.cartoonmad.com/comic/1024.html",proxy);
        Assert.assertNotNull(comic);
    }

    @Test
    public void getCover(){
//        Proxy proxy=new Proxy("1.198.72.22",9999,"http");
        byte[] pic=HttpUtil.get("https://www.cartoonmad.com/cartoonimgs/coimg/1412.jpg");
        Assert.assertNotNull(pic);
        IOUtil.writeFile(GlobalConfig.ROOT_PATH +"1412.jpg", pic);
    }

    @Test
    public void getFile(){
        Map<String,String> headers=new HashMap<>();
        headers.put("Referer", "https://www.cartoonmad.com/comic/141100012033001.html");
        byte[] data=HttpUtil.get("https://www.cartoonmad.com/comic/comicpic.asp?file=/1411/001/001&rimg=1",null,headers);
        Assert.assertNotNull(data);
        IOUtil.writeFile(GlobalConfig.ROOT_PATH+"001.jpg", data);
    }

    @Test
    public void zipTest(){
        Map<String,byte[]> data=IOUtil.zipFileInput(GlobalConfig.ROOT_PATH+"第001话");
        Assert.assertEquals(14, data.size());
    }
}
