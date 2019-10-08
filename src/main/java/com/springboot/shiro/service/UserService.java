package com.springboot.shiro.service;

import com.springboot.shiro.pojo.UserAuthority;

public interface UserService {

    UserAuthority findByUsername(String username);
}
