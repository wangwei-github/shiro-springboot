package com.springboot.shiro.mapper;

import com.springboot.shiro.pojo.UserAuthority;

public interface UserMapper {

    UserAuthority findByUsername(String username);
}
