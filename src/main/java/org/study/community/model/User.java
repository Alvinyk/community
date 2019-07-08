package org.study.community.model;

import lombok.Data;

/**
 * @author yangkai
 * @description 数据库用户表对象
 * @date 2019/6/16 15:33
 **/
@Data
public class User {
    private Integer id;
    private String name;
    private String bio;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
