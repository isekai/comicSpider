package com.comicspider.cartoonmad.downloader;

import com.comicspider.cartoonmad.dto.Cartoonmad;
import com.comicspider.entity.*;
import com.comicspider.service.*;
import com.comicspider.utils.HttpUtil;
import com.hankcs.hanlp.HanLP;
import lombok.Setter;
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
public class ComicDlTask implements Runnable {
    @Setter
    private Map<String,Proxy> comicForDownload;
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

    public ComicDlTask(ComicService comicService, ChapterService chapterService, TagService tagService, ComicTagService comicTagService, RedisService redisService) {
        this.comicService = comicService;
        this.chapterService = chapterService;
        this.tagService = tagService;
        this.comicTagService = comicTagService;
        this.redisService = redisService;
    }

    @Override
    public void run() {
        if (comicForDownload.size()!=0){
            for (String url : comicForDownload.keySet()){
                try {
                    getComic(url, comicForDownload.get(url));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void getComic(String comicUrl, Proxy proxy) throws UnsupportedEncodingException {
        String html=new String(HttpUtil.get(comicUrl, proxy),"Big5");
        Cartoonmad cartoonmad= getCartoonmad(html);
        Comic comic=cartoonmad.getComic();
        List<Tag> tags=cartoonmad.getTags();
        Map<String, Chapter> chapters=cartoonmad.getChapters();
        comic.setCover(Base64.getEncoder().encodeToString(HttpUtil.get(comic.getCover(), proxy)));
        comicService.saveOrUpdate(comic);
        comic.setComicId(comicService.findByComicName(comic.getComicName()).getComicId());
        for (Tag tag : tags) {
            if (tagService.findByTagName(tag.getTagName()) == null) {
                tagService.saveOrUpdate(tag);
            }
            ComicTag comicTag = new ComicTag(comic.getComicId(), tagService.findByTagName(tag.getTagName()).getTagId());
            if (comicTagService.findByComicIdAndTagId(comicTag.getComicId(), comicTag.getTagId()) == null) {
                comicTagService.saveOrUpdate(comicTag);
            }
        }
        for (String url : chapters.keySet()){
            chapters.get(url).setComicId(comic.getComicId());
            redisService.set(url,chapters.get(url));
            chapterService.saveOrUpdate(chapters.get(url));
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
        String[] tagNames=str[5].split("\\s+");
        for (String tagName : tagNames) {
            tags.add(new Tag(tagName));
        }
        Element tdStyle=doc.selectFirst("table[width=800] td[style=\"font-size:11pt;\"]");
        String about=HanLP.convertToSimplifiedChinese(tdStyle.text().replace("<(\"[^\"]*\"|'[^']*'|[^'\">])*>", ""));
        comic.setAbout(about);
        Pattern pattern1=Pattern.compile("/comic/[a-zA-Z0-9]{10,30}");
        Matcher matcher=pattern1.matcher(html);
        while (matcher.find()){
            String pageUrl=matcher.group().replace("/comic/", "");
            String chapterId=pageUrl.substring(pageUrl.length()-10,pageUrl.length()-7);
            String chapterType=pageUrl.substring(pageUrl.length()-7,pageUrl.length()-6);
            String chapterName;
            if (chapterType.equals("1")){
                chapterName="第"+chapterId+"卷";
            }
            else {
                chapterName="第"+chapterId+"话";
            }
            int pageNum=Integer.parseInt(pageUrl.substring(pageUrl.length()-6,pageUrl.length()-3));
            chapters.put("https://www.cartoonmad.com/comic/"+pageUrl,new Chapter(pageNum,chapterName));
        }
        comic.setChapterNum(chapters.size());
        Pattern pattern2=Pattern.compile("/cartoonimgs/coimg/[a-zA-Z0-9]{1,10}.[a-zA-Z0-9]{1,5}");
        matcher=pattern2.matcher(html);
        if (matcher.find()){
            comic.setCover("https://www.cartoonmad.com"+matcher.group());
        }
        return new Cartoonmad(comic,tags,chapters);
    }

}
