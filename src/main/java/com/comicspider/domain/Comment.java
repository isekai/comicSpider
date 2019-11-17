package com.comicspider.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 评论实体
 *
 * @author doctor
 * @date 2019/10/27
 **/
@Entity
@Table(name = "comicspider_comment")
@Data
public class Comment extends AbstractEntity {

    @JsonIgnore
    private String id;
    @JsonIgnore
    private String comicId;
    @JsonIgnore
    private String userId;
    private String commentContent;
    private int starNum;
    private boolean isCheck;
    private boolean isDelete;

}
