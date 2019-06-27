package com.comicspider.cartoonmad.downloader;

import com.comicspider.config.GlobalConfig;
import com.comicspider.entity.Chapter;
import com.comicspider.entity.Proxy;
import com.comicspider.enums.DownloadedEnum;
import com.comicspider.exception.SpiderException;
import com.comicspider.service.ChapterService;
import com.comicspider.service.ComicService;
import com.comicspider.service.RedisService;
import com.comicspider.utils.HttpUtil;
import com.comicspider.utils.IOUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author doctor
 * @Date 19-6-7
 **/
@Slf4j
public class FileDlTask implements Runnable{
    @Setter
    private List<Proxy> proxies;
    @Setter
    private ComicService comicService;
    @Setter
    private RedisService redisService;
    @Setter
    private ChapterService chapterService;

    private Map<String,byte[]> data=new LinkedHashMap<>();

    public FileDlTask(ComicService comicService,RedisService redisService, ChapterService chapterService) {
        this.comicService=comicService;
        this.redisService = redisService;
        this.chapterService = chapterService;
    }

    @Override
    public void run() {
        String url;
        Chapter chapter;
        while (true){
            synchronized (this){
                url=redisService.randomKey();
                log.info("获取key！"+url);
                if (url==null){
                    break;
                }
                chapter=(Chapter) redisService.get(url);
                redisService.delete(url);
            }
            download(url, chapter,proxies.get((int)(Math.random()*proxies.size())));
        }

    }

    private void download(String url, Chapter chapter, Proxy proxy){
        log.info("开始下载漫画章节！");
        String path= GlobalConfig.ROOT_PATH+chapter.getComicId();
        String comicId=url.substring(url.length()-15,url.length()-11);
        String chapterId=url.substring(url.length()-10,url.length()-7);
        String requestUrl="https://www.cartoonmad.com/comic/comicpic.asp?file=/"+comicId+"/"+chapterId+"/";
        String refererUrl=url.substring(0,url.length()-3);
        String num;
        for (int i=1;i<chapter.getPageNum()+1;i++){
            num=String.format("%03d", i);
            log.info("正在下载第"+num+"页...");
            for (int j=0;j<3;j++){
                try {
                    byte[] fileByte= HttpUtil.get(requestUrl+num+"&rimg=1", proxy,refererUrl+num);
                    if (fileByte.length>0){
                        data.put(num+".jpg", fileByte);
                        break;
                    }
                } catch (SpiderException e){
                    log.info("第"+i+"次下载图片失败！ "+e.getMsg());
                }
            }
            if (data.size()<i){
                log.info("下载章节失败！");
                break;
            }
        }
        if (data.size()==chapter.getPageNum()){
            Chapter newChapter=chapterService.findById(chapter.getChapterId());
            newChapter.setDownloaded(DownloadedEnum.DOWNLOADED.getCode());
            chapterService.saveOrUpdate(newChapter);
            IOUtil.zipFileOutput(path+chapter.getChapterId(), data);
        }
        else {
            redisService.set(url, chapter);
        }
    }

}
