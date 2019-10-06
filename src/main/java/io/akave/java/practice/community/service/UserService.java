package io.akave.java.practice.community.service;

import io.akave.java.practice.community.dto.GithubUser;
import io.akave.java.practice.community.mapper.UserMapper;
import io.akave.java.practice.community.model.User;
import io.akave.java.practice.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author akave
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public boolean createOrUpdate(GithubUser githubUser, String token) {
        try {
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andAccountIdEqualTo(githubUser.getId());
            List<User> users = userMapper.selectByExample(userExample);
            if (users == null || users.size() == 0) {
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
                User dbUser = users.get(0);
                User updateUser = new User();
                updateUser.setToken(token);
                updateUser.setName(githubUser.getName());
                updateUser.setAvatarUrl(githubUser.getAvatarUrl());
                updateUser.setGmtModified(System.currentTimeMillis());
                updateUser.setBio(githubUser.getBio());
                UserExample updateExample = new UserExample();
                updateExample.createCriteria().andIdEqualTo(dbUser.getId());
                userMapper.updateByExampleSelective(updateUser, updateExample);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
