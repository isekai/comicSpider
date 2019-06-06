package com.comicspider.cartoonmad.downloader;

import com.comicspider.cartoonmad.dto.Cartoonmad;
import com.comicspider.cartoonmad.parser.HtmlParser;
import com.comicspider.entity.*;
import com.comicspider.service.ChapterService;
import com.comicspider.service.ComicService;
import com.comicspider.service.ComicTagService;
import com.comicspider.service.TagService;
import com.comicspider.utils.HttpUtil;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/** 漫画爬虫线程
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

    public ComicDlTask(ComicService comicService, ChapterService chapterService, TagService tagService, ComicTagService comicTagService) {
        this.comicService = comicService;
        this.chapterService = chapterService;
        this.tagService = tagService;
        this.comicTagService = comicTagService;
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
        String html=new String(HttpUtil.getByte(comicUrl, proxy),"Big5");
        Cartoonmad cartoonmad= HtmlParser.getCartoonmad(html);
        Comic comic=cartoonmad.getComic();
        List<Tag> tags=cartoonmad.getTags();
        Map<String, Chapter> chapters=cartoonmad.getChapters();
        comic.setCover(Base64.getEncoder().encodeToString(HttpUtil.getByte(comic.getCover(), proxy)));
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
            chapterService.saveOrUpdate(chapters.get(url));
        }
    }
}
