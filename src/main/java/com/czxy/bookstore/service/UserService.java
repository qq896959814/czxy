package com.czxy.bookstore.service;

import com.czxy.bookstore.dao.UserMapper;
import com.czxy.bookstore.domain.User;
import com.czxy.bookstore.utils.MybatisUtils;

import java.io.IOException;
import java.util.List;


public class UserService {
    public List<User> findAll() throws IOException {

        UserMapper mapper = MybatisUtils.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        MybatisUtils.commitAndclose();
        return users;
    }

    //根据用户名,获取用户
    public User findByUsername(User user) throws IOException {
        UserMapper mapper = MybatisUtils.getMapper(UserMapper.class);
        User exUser = mapper.selectOne(user);
        MybatisUtils.commitAndclose();
        return exUser;
    }

    /**
     * service登陆逻辑
     * @param user
     * @return
     * @throws IOException
     */
    public User login(User user) throws IOException {
        //方法1
        UserMapper mapper = MybatisUtils.getMapper(UserMapper.class);
        //获取所有的用户
        List<User> users = mapper.selectAll();
        //迭代所有用户,对比账号密码
        for (User thisUser : users) {
            if(thisUser.getUsername().equals(user.getUsername())&&thisUser.getPassword().equals(user.getPassword()))
                //发现匹配项,返回匹配项
                return thisUser;
        }
        //没有任何对象返回,返回null
        return null;

//        //方法2
//        UserMapper mapper = MybatisUtils.getMapper(UserMapper.class);
//        Example example = new Example(User.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("username",user.getUsername());
//        criteria.andEqualTo("password",user.getPassword());
//        User user1 = mapper.selectOneByExample(example);
//        return user1;
    }
}
