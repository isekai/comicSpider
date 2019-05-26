package com.comicspider.utils;

import com.comicspider.cartoonmad.parser.HtmlParser;
import com.comicspider.config.GlobalConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

/**
 * @Author doctor
 * @Date 19-5-23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UtilsTest {

    @Test
    public void getPic(){
//        Proxy proxy=new Proxy("1.198.72.22",9999,"http");
        byte[] pic=HttpUtil.get("https://www.cartoonmad.com/cartoonimgs/coimg/1412.jpg",null);
        Assert.assertNotNull(pic);
        System.out.println(GlobalConfig.ROOT_PATH);
        IOUtil.writeFile(GlobalConfig.ROOT_PATH +"1412.jpg", pic);
    }

    @Test
    public void getComic(){
        byte[] html=HttpUtil.get("https://www.cartoonmad.com/comic/1412.html", null);
        Assert.assertNotNull(html);
        try {
            HtmlParser.getComic(new String(html,"Big5"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
