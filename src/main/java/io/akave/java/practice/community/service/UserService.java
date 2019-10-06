package io.akave.java.practice.community.service;

import io.akave.java.practice.community.dto.GithubUser;
import io.akave.java.practice.community.mapper.UserMapper;
import io.akave.java.practice.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author akave
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public boolean createOrUpdate(GithubUser githubUser, String token) {
        try {
            User dbUser = userMapper.findUserByAccountId(githubUser.getId());
            if (dbUser == null) {
                User user = new User();
                user.setToken(token);
                user.setName(githubUser.getName());
                user.setAccountId(String.valueOf(githubUser.getId()));
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                user.setBio(githubUser.getBio());
                user.setAvatarUrl(githubUser.getAvatarUrl());
                userMapper.insert(user);
            } else {
                dbUser.setToken(token);
                dbUser.setName(githubUser.getName());
                dbUser.setAvatarUrl(githubUser.getAvatarUrl());
                dbUser.setGmtModified(System.currentTimeMillis());
                dbUser.setBio(githubUser.getBio());
                userMapper.update(dbUser);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
