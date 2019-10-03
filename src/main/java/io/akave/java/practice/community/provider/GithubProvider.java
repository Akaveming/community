package io.akave.java.practice.community.provider;

import com.alibaba.fastjson.JSON;
import io.akave.java.practice.community.dto.AccessTokenDTO;
import io.akave.java.practice.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getGithubUser(String token) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?" + token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return JSON.parseObject(response.body().string(),GithubUser.class) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
