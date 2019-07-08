package org.study.community.dto;

import lombok.Data;

/**
 * @author yangkai
 * @description TODO
 * @date 2019/6/16 12:02
 **/
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
