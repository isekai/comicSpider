package com.comicspider.domain.dto.response;

import com.comicspider.domain.Author;
import com.comicspider.domain.Tag;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author doctor
 * @date 2019/11/3
 **/
@Data
@NoArgsConstructor
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
    private Integer clickNum;
    private Integer status;

    private String chapterName;
    private Integer chapterNum;
    private Integer pageSum;
    private Integer chapterType;

    private List<Author> authors;
    private List<Tag> tags;

    private Date updateTime;
}
