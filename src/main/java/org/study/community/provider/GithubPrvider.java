package org.study.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.study.community.dto.AccessTokenDTO;
import org.study.community.dto.GithubUser;

import java.io.IOException;

/**
 * @author yangkai
 * @description github 授权方法调用
 * @date 2019/6/16 11:59
 **/
@Component
public class GithubPrvider {

    /**
     * @return java.lang.String
     * @description 获取GitHub 的accessToken 数据
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String info = response.body().string();
            String token = info.substring(info.indexOf("=")+1,info.indexOf("&"));
            System.out.println("accessToken:" + token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param accessToken
     * @return org.study.community.dto.GithubUser
     * @description 根据accessToken 数据获取用户数据
     */
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String info = response.body().string();
            GithubUser user = JSON.parseObject(info, GithubUser.class);
            return user;
        } catch (IOException e) {

        }

        return null;
    }
}
