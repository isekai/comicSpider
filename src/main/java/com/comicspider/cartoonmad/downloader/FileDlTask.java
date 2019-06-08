package com.comicspider.cartoonmad.downloader;

import com.comicspider.entity.Chapter;
import com.comicspider.entity.Proxy;
import com.comicspider.exception.SpiderException;
import com.comicspider.utils.HttpUtil;
import com.comicspider.utils.IOUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author doctor
 * @Date 19-6-7
 **/
@Slf4j
public class FileDlTask implements Runnable{
    @Setter
    private String basePath;
    @Setter
    private String baseUrl;
    @Setter
    private Chapter chapter;
    @Setter
    private Proxy proxy;
    private Map<String,byte[]> data=new LinkedHashMap<>();

    @Override
    public void run() {
        String comicId=baseUrl.substring(baseUrl.length()-15,baseUrl.length()-11);
        String chapterId=baseUrl.substring(baseUrl.length()-10,baseUrl.length()-7);
        String requestUrl="https://www.cartoonmad.com/comic/comicpic.asp?file=/"+comicId+"/"+chapterId+"/";
        String refererUrl=baseUrl.substring(0,baseUrl.length()-3);
        String num;
        for (int i=1;i<chapter.getPageNum()-10;i++){
            num=String.format("%03d", i);
            log.info("正在下载第"+num+"页...");
            try {
                byte[] fileByte= HttpUtil.get(requestUrl+num+"&rimg=1", proxy,refererUrl+num);
                if (fileByte.length>0){
                    data.put("00"+i+".jpg", fileByte);
                }
            } catch (SpiderException e){
                    log.info("下载失败！ "+e.getMsg());
            }
        }
        IOUtil.ZipFileOutput(basePath+chapter.getChapterName(), data);
    }

}
