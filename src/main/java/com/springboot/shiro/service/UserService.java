package com.springboot.shiro.service;

import com.springboot.shiro.pojo.UserAuthority;

public interface UserService {

    UserAuthority selectByUsername(String username);
    String selectPermsByUsername(String username);
}
