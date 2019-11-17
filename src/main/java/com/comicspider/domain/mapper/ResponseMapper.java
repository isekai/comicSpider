package com.comicspider.domain.mapper;

import com.comicspider.domain.Chapter;
import com.comicspider.domain.Comic;
import com.comicspider.domain.dto.response.LastUpdateResponseDTO;

/**
 * @author doctor
 * @date 2019/11/5
 **/
public class ResponseMapper {

    public static LastUpdateResponseDTO getLastUpdate(Comic comic, Chapter chapter) {
        LastUpdateResponseDTO responseDTO = new LastUpdateResponseDTO();

        responseDTO.setCategory(comic.getCategory());
        responseDTO.setComicName(comic.getComicName());
        responseDTO.setAliasName(comic.getAliasName());
        responseDTO.setRealName(comic.getRealName());
        responseDTO.setComicType(comic.getComicType());
        responseDTO.setLanguage(comic.getLanguage());
        responseDTO.setCountry(comic.getCountry());
        responseDTO.setPublish(comic.getPublish());
        responseDTO.setCover(comic.getCover());
        responseDTO.setDescription(comic.getDescription());
        responseDTO.setClickNum(comic.getClickNum());
        responseDTO.setStatus(comic.getStatus());
        responseDTO.setChapterName(chapter.getChapterName());
        responseDTO.setChapterNum(chapter.getChapterNum());
        responseDTO.setPageSum(chapter.getPageSum());
        responseDTO.setChapterType(chapter.getChapterType());
        responseDTO.setUpdateTime(chapter.getUpdateTime());

        return responseDTO;
    }

}
