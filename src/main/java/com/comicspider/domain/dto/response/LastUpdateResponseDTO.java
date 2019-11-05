package com.comicspider.domain.dto.response;

import com.comicspider.domain.Comic;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author doctor
 * @date 2019/11/3
 **/
@Data
public class LastUpdateResponseDTO {

    private String category;
    private String comicName;
    private String aliasName;
    private String realName;
    private String comicType;
    private String language;
    private String country;
    private String publish;
    private String cover;
    private String description;
    private int clickNum;
    private int status;

    private String authorName;
    private String authorAlias;
    private String authorCountry;
    private String socialAccount;

    private String chapterName;
    private Integer chapterNum;
    private Integer pageSum;
    private Integer chapterType;

    private List<String> tags;

    private Date updateTime;
}
