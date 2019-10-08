package com.springboot.shiro.mapper;

import com.springboot.shiro.pojo.UserAuthority;

public interface UserMapper {

    UserAuthority findUserByUsername(String username);
    String findPermsByUsername(String username);
}
