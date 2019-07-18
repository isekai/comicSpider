package com.comicspider.cartoonmad.downloader;

import com.comicspider.config.GlobalConfig;
import com.comicspider.entity.Chapter;
import com.comicspider.entity.Proxy;
import com.comicspider.enums.DownloadedEnum;
import com.comicspider.exception.SpiderException;
import com.comicspider.service.ChapterService;
import com.comicspider.service.RedisService;
import com.comicspider.utils.HttpUtil;
import com.comicspider.utils.IOUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Author doctor
 * @Date 19-6-7
 **/
@Slf4j
public class FileDlTask implements Runnable{
    @Setter
    private String rootPath;
    @Setter
    private List<Proxy> proxies;
    @Setter
    private RedisService redisService;
    @Setter
    private ChapterService chapterService;

//    private Map<String,byte[]> data=new LinkedHashMap<>();
    private List<String> data=new ArrayList<>();

    public FileDlTask(RedisService redisService, ChapterService chapterService) {
        this.redisService = redisService;
        this.chapterService = chapterService;
    }

    @Override
    public void run() {
        String url;
        Chapter chapter;
        while (true){
            synchronized (FileDlTask.class){
                url=redisService.randomKey();
                if (url==null){
                    break;
                }
                chapter=(Chapter) redisService.get(url);
                redisService.delete(url);
            }
            Proxy proxy=null;
            if (proxies.size()>0){
                proxy=proxies.get((int)(Math.random()*proxies.size()));
            }
            download(url, chapter,proxy);
        }

    }

    private void download(String url, Chapter chapter,Proxy proxy){
        log.info("开始下载漫画章节！");
        int failNum=0;
/*        String path= rootPath+chapter.getComicId();*/
        String comicId=url.substring(url.length()-15,url.length()-11);
        String chapterId=url.substring(url.length()-10,url.length()-7);
        String requestUrl="https://www.cartoonmad.com/comic/comicpic.asp?file=/"+comicId+"/"+chapterId+"/";
        String refererUrl=url.substring(0,url.length()-3);
        String num;
/*        for (int i=1;i<chapter.getPageNum()+1;i++){
            num=String.format("%03d", i);
            log.info("正在下载第"+num+"页...");
            Map<String,String> headers=new HashMap<>();
            headers.put("Referer", refererUrl+num);
            for (int j=0;j<3;j++){
                try {
                    byte[] fileByte= HttpUtil.get(requestUrl+num+"&rimg=1", proxy,headers);
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
            IOUtil.zipFileOutput(path+"/"+chapter.getChapterId(), data);
        }
        else {
            redisService.set(url, chapter);
        }*/
        for (int i=1;i<chapter.getPageNum()+1;i++){
            num=String.format("%03d", i);
            String path=rootPath+chapter.getComicId()+"/"+chapter.getChapterId()+"/"+num;
            Map<String,String> headers=new HashMap<>(16);
            headers.put("Referer", refererUrl+num);
            try {
                byte[] fileByte= HttpUtil.get(requestUrl+num+"&rimg=1", proxy,headers);
                if (fileByte.length>0){
                    log.info("正在下载第"+num+"页...");
                    IOUtil.writeFile(path, fileByte);
                }
                else {
                    data.add(num);
                    log.info("下载图片"+num+"失败！");
                }
            } catch (SpiderException e){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        while (data.size()>0){
            num=data.get(0);
            String path=rootPath+chapter.getComicId()+"/"+chapter.getChapterId()+"/"+num;
            Map<String,String> headers=new HashMap<>(16);
            headers.put("Referer", refererUrl+num);
            try {
                byte[] fileByte= HttpUtil.get(requestUrl+num+"&rimg=1", proxy,headers);
                if (fileByte.length>0){
                    data.remove(num);
                    IOUtil.writeFile(path, fileByte);
                }else {
                    log.info("下载图片"+num+"失败！正在重试！");
                    failNum++;
                }
            }catch (SpiderException e){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            if (failNum>10){
                log.info("章节下载失败！");
                redisService.set(url,chapter);
                break;
            }
        }
        if (data.size()==0){
            Chapter newChapter=chapterService.findById(chapter.getChapterId());
            newChapter.setDownloaded(DownloadedEnum.DOWNLOADED.getCode());
            chapterService.saveOrUpdate(newChapter);
        }
    }

}
