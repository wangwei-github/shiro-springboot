package com.springboot.shiro.pojo;

/**
 * @author wangwei
 * @version 1.0
 * @date 2019/10/8 18:12
 */
public class UserAuthority {
    private Integer uid;
    private String username;
    private String password;
    private String perms;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }
}
