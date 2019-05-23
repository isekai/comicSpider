package com.comicspider.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author doctor
 * @Date 19-5-23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {

    @Test
    public void getPic(){
        byte[] pic=HttpUtil.get("https://www.cartoonmad.com/cartoonimgs/coimg/1412.jpg", null);
        IOUtil.writeFile("/home/doctor/workspace/test/"+"1412.jpg", pic);
    }
}
