package org.study.community.dto;

import lombok.Data;
import org.study.community.model.User;

/**
 * @author yangkai
 * @description TODO
 * @date 2019/7/5 13:37
 **/
@Data
public class QuestionDTO {
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
    private User user;
}
