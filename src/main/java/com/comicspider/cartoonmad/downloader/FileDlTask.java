package com.comicspider.cartoonmad.downloader;

import com.comicspider.entity.Chapter;
import com.comicspider.entity.Proxy;
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
    private int[] comicIds;
    @Setter
    private String rootPath;
    @Setter
    private List<Proxy> proxies;
    @Setter
    private RedisService redisService;
    @Setter
    private ChapterService chapterService;

    private List<String> data=new ArrayList<>();

    public FileDlTask(RedisService redisService,ChapterService chapterService) {
        this.redisService = redisService;
        this.chapterService = chapterService;
    }

    @Override
    public void run() {
        Proxy proxy=null;
        for (int comicId : comicIds){
            String key=String.valueOf(comicId);
            if (redisService.hasKey(key)){
                while (true){
                    String url= (String) redisService.leftPop(key);
                    if (proxies.size()>0){
                        proxy=proxies.get((int)(Math.random()*proxies.size()));
                    }
                    if (url==null){
                        break;
                    }
                    download(url,proxy);
                }
            }
        }
    }

    private void download(String url,Proxy proxy){
        log.info("开始下载漫画章节！");
        int failNum=0;
        Chapter chapter= new Chapter();
        chapter.setComicId(Integer.parseInt(url.substring(url.length()-15,url.length()-11)));
        chapter.setChapterName(url.substring(url.length()-10,url.length()-7));
        chapter.setPageNum(Integer.parseInt(url.substring(url.length()-6,url.length()-3)));
        chapter.setChapterType(Integer.parseInt(url.substring(url.length()-7,url.length()-6)));
        String requestUrl="https://www.cartoonmad.com/comic/comicpic.asp?file=/"+chapter.getComicId()+"/"+chapter.getChapterName()+"/";
        String refererUrl=url.substring(0,url.length()-3);
        String num;
        for (int i=1;i<chapter.getPageNum()+1;i++){
            num=String.format("%03d", i);
            String path=rootPath+chapter.getComicId()+"/"+chapter.getChapterName()+"/"+num;
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
            String path=rootPath+chapter.getComicId()+"/"+chapter.getChapterName()+"/"+num;
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
                redisService.leftPush(String.valueOf(chapter.getComicId()), url);
                break;
            }
        }
        if (data.size()==0){
            chapterService.saveOrUpdate(new Chapter());
        }
    }

}
