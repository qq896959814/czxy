package com.czxy.service;


import com.czxy.dao.UserMapper;
import com.czxy.domain.User;
import com.czxy.utils.MybatisUtils;

import java.io.IOException;
import java.util.List;

public class UserService {
    public User login(User user) throws IOException {
        UserMapper mapper = MybatisUtils.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())){
                return u;
            }
        }
        return null;
    }
}
