package org.study.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.community.dto.AccessTokenDTO;
import org.study.community.dto.GithubUser;
import org.study.community.mapper.UserMapper;
import org.study.community.model.User;
import org.study.community.provider.GithubPrvider;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

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

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubPrvider.getAccessToken(accessTokenDTO);

        GithubUser githubUser = githubPrvider.getUser(accessToken);
        if (Objects.nonNull(githubUser)) {
            //登录成功 写session cookie
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);

            request.getSession().setAttribute("user", githubUser);
            return "redirect:/";
        } else {
            //登录失败，重新登录
            return "redirect:/";
        }
    }
}
