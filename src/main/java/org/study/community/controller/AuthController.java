package org.study.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.community.dto.AccessTokenDTO;
import org.study.community.dto.GithubUser;
import org.study.community.provider.GithubPrvider;

/**
 * @author yangkai
 * @description github auth
 * @date 2019/6/16 11:53
 **/
@Controller
public class AuthController {

    @Autowired
    private GithubPrvider githubPrvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubPrvider.getAccessToken(accessTokenDTO);

        GithubUser user = githubPrvider.getUser(accessToken);
        System.out.println("UserName:"+user.getName());

        return "index";
    }
}
