package org.study.community.model;

import lombok.Data;

/**
 * @author yangkai
 * @description 数据库问题对象
 * @date 2019/6/19 12:39
 **/
@Data
public class Question {
    private Integer id;
    private String tag;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
}
