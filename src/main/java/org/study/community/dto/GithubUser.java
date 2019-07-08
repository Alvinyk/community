package org.study.community.dto;

import lombok.Data;

/**
 * @author yangkai
 * @description github 用户信息
 * @date 2019/6/16 12:29
 **/
@Data
public class GithubUser {
    private String name;
    private long id;
    private String bio;
    private String avatarUrl;
}
