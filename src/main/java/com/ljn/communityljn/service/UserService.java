package com.ljn.communityljn.service;

import com.ljn.communityljn.dao.UserMapper;
import com.ljn.communityljn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author li
 * @Date 10/29/22 9:34 PM
 * @Version 1.0
 * 描述 ：用户业务层的开发
 * 名称：UserService
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){

        return userMapper.selectById(id);
    }
}
