package com.springboot.shiro.service;

import com.springboot.shiro.mapper.UserMapper;
import com.springboot.shiro.pojo.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangwei
 * @version 1.0
 * @date 2019/10/8 18:19
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserAuthority findByUsername(String username) {

        return userMapper.findByUsername(username);
    }
}
