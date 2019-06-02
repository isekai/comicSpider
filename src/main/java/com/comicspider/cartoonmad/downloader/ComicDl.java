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
import org.springframework.data.redis.core.RedisTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/** 漫画爬虫线程
 * @Author doctor
 * @Date 19-6-2
 **/
public class ComicDl implements Runnable {
    @Setter
    private String comicUrl;
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

    @Override
    public void run() {

    }

    private void getComic(String comicUrl, Proxy proxy) throws UnsupportedEncodingException {
        String html=new String(HttpUtil.get(comicUrl, proxy),"Big5");
        Cartoonmad cartoonmad= HtmlParser.getCartoonmad(html);
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
        chapterService.saveKeyVaule(chapters);
    }
}
