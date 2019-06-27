package com.comicspider.cartoonmad.downloader;

import com.comicspider.cartoonmad.dto.Cartoonmad;
import com.comicspider.config.GlobalConfig;
import com.comicspider.entity.*;
import com.comicspider.enums.DownloadedEnum;
import com.comicspider.enums.EndEnum;
import com.comicspider.enums.ExceptionEnum;
import com.comicspider.exception.SpiderException;
import com.comicspider.service.*;
import com.comicspider.utils.HttpUtil;
import com.hankcs.hanlp.HanLP;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 漫画爬虫线程任务
 * @Author doctor
 * @Date 19-6-2
 **/
@Slf4j
public class ComicDlTask implements Runnable {
    @Setter
    private int[] comicIds;
    @Setter
    private Proxy proxy;
    @Setter
    private ComicService comicService;
    @Setter
    private ChapterService chapterService;
    @Setter
    private TagService tagService;
    @Setter
    private ComicTagService comicTagService;
    @Setter
    private RedisService redisService;

    private static Pattern pattern1=Pattern.compile("/cartoonimgs/coimg/[a-zA-Z0-9]{1,10}.[a-zA-Z0-9]{1,5}");
    private static Pattern pattern2=Pattern.compile("/image/chap([a-zA-Z0-9]{1})");
    private static Pattern pattern3=Pattern.compile("/comic/[a-zA-Z0-9]{10,30}");

    public ComicDlTask(ComicService comicService, ChapterService chapterService, TagService tagService, ComicTagService comicTagService, RedisService redisService) {
        this.comicService = comicService;
        this.chapterService = chapterService;
        this.tagService = tagService;
        this.comicTagService = comicTagService;
        this.redisService = redisService;
    }

    @Override
    public void run() {
        for (int comicId : comicIds) {
            try {
                getComic(comicId, proxy);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (SpiderException e) {
                log.info(comicId+"-----------------"+proxy.getIp()+"--"+proxy.getType());
                Comic comic = new Comic();
                comic.setComicId(comicId);
                log.info("下载漫画失败！ "+e.getMsg());
            }
        }
    }

    private void getComic(int comicId, Proxy proxy) throws SpiderException, UnsupportedEncodingException {
        log.info("开始下载漫画！");
        String comicUrl= GlobalConfig.CARTOONMAD_BASE_URL +comicId+".html";
        String html=new String(HttpUtil.get(comicUrl, proxy),"Big5-HKSCS");
        Cartoonmad cartoonmad= getCartoonmad(html);
        Comic comic=cartoonmad.getComic();
        List<Tag> tags=cartoonmad.getTags();
        Map<String, Chapter> chapters=cartoonmad.getChapters();
        Chapter chapter;
        comic.setComicId(comicId);
        comic.setDownloaded(DownloadedEnum.DOWNLOADED.getCode());
        comic.setCover(Base64.getEncoder().encodeToString(HttpUtil.get(comic.getCover(), proxy)));
        comicService.saveOrUpdate(comic);
        for (Tag tag : tags) {
            if (tagService.findByTagName(tag.getTagName()) == null) {
                tagService.saveOrUpdate(tag);
            }
            ComicTag comicTag = new ComicTag(comicId, tagService.findByTagName(tag.getTagName()).getTagId());
            if (comicTagService.findByComicIdAndTagId(comicId, comicTag.getTagId()) == null) {
                comicTagService.saveOrUpdate(comicTag);
            }
        }
        for (String url : chapters.keySet()){
            chapter=chapters.get(url);
            chapter.setChapterId(Integer.parseInt(comic.getComicId()+"0"+chapter.getChapterId()));
            chapter.setComicId(comicId);
            redisService.set(url,chapter);
            chapterService.saveOrUpdate(chapter);
        }
    }

    private Cartoonmad getCartoonmad(String html){
        Comic comic=new Comic();
        List<Tag> tags=new LinkedList<>();
        Map<String,Chapter> chapters=new LinkedHashMap<>();
        Document doc= Jsoup.parse(html);
        Element title=doc.getElementsByTag("title").first();
        String comicName= HanLP.convertToSimplifiedChinese(title.text().split("\\s")[0]);
        comic.setComicName(comicName);
        Elements td=doc.select("td[width=300][height=24]");
        String[] str=new String[6];
        for (int i=0;i<td.size();i++){
            str[i]=td.get(i).text().replaceAll("[\\u4e00-\\u9fa5]+：\\s+", "");
        }
        String category= HanLP.convertToSimplifiedChinese(str[0].replaceAll("\\s+",""));
        comic.setCategory(category);
        String author=str[1].replaceAll("\\s+","");
        comic.setAuthor(author);
        Element tdStyle=doc.selectFirst("table[width=800] td[style=\"font-size:11pt;\"]");
        String about=HanLP.convertToSimplifiedChinese(tdStyle.text().replace("<(\"[^\"]*\"|'[^']*'|[^'\">])*>", ""));
        comic.setAbout(about);
        Matcher matcher=pattern1.matcher(html);
        if (matcher.find()){
            comic.setCover("https://www.cartoonmad.com"+matcher.group());
        }
        matcher=pattern2.matcher(html);
        if (matcher.find()){
            if ("1".equals(matcher.group(1))){
                comic.setEnd(EndEnum.END.getCode());
            }
            else {
                comic.setEnd(EndEnum.UN_END.getCode());
            }
        }

        String[] tagNames=str[5].split("\\s+");
        for (String tagName : tagNames) {
            tags.add(new Tag(HanLP.convertToSimplifiedChinese(tagName)));
        }

        matcher=pattern3.matcher(html);
        while (matcher.find()){
            String pageUrl=matcher.group().replace("/comic/", "");
            int chapterId=Integer.parseInt(pageUrl.substring(pageUrl.length()-10,pageUrl.length()-7));
            int chapterType=Integer.parseInt(pageUrl.substring(pageUrl.length()-7,pageUrl.length()-6));
            String chapterName;
            if (chapterType==1){
                chapterName="第"+chapterId+"卷";
            }
            else {
                chapterName="第"+chapterId+"话";
            }
            int pageNum=Integer.parseInt(pageUrl.substring(pageUrl.length()-6,pageUrl.length()-3));
            Chapter chapter=new Chapter();
            chapter.setChapterId(chapterId);
            chapter.setChapterType(chapterType);
            chapter.setChapterName(chapterName);
            chapter.setPageNum(pageNum);
            chapters.put(GlobalConfig.CARTOONMAD_BASE_URL+pageUrl,chapter);
        }
        comic.setChapterNum(chapters.size());
        return new Cartoonmad(comic,tags,chapters);
    }

}
