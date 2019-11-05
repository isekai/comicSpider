package com.comicspider.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户实体
 *
 * @author doctor
 * @date 2019/10/27
 **/
@Entity
@Table(name = "comicspider_user")
@Data
public class User extends AbstractEntity {

    private String id;
    private String userName;
    private String nickname;
    private String userPassword;
    private String salt;
    private String userMail;
    private String userAvatar;
    private String socialAccount;
    private String userDes;
    private boolean isBlock;
    private boolean isDelete;

}
