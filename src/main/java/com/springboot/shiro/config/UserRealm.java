package com.springboot.shiro.config;

import com.springboot.shiro.pojo.UserAuthority;
import com.springboot.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 *
 * @author wangwei
 * @version 1.0
 * @date 2019/10/8 14:28
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录用户
        //获取资源权限
        Subject subject = SecurityUtils.getSubject();
        //  获得认证逻辑返回的第一个参数里面的值 return new SimpleAuthenticationInfo(user, user.getPassword(), "");
        UserAuthority user = (UserAuthority)subject.getPrincipal();
        String perms = userService.selectPermsByUsername(user.getUsername());
        System.err.println("perms:"+perms);
        if (perms.equals(""))
            perms="user:init";
        System.err.println("perms2:"+perms);
        //添加资源的授权字符串
        info.addStringPermission(perms);
        return info;
    }

    @Autowired
    private UserService userService;

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
      /*  //假设数据库用户名、密码
        String name = "wangwei";
        String password = "123456";*/
        //编写判断逻辑
        //判断用户
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserAuthority user = userService.selectByUsername(token.getUsername());
        if (user==null) {
            return null;//会抛出UnknownAccountException
        }
        //判断密码
        /**
         * arg1 返回的数据，arg2 数据库用户的密码，arg3 Shiro的名字
         */
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
