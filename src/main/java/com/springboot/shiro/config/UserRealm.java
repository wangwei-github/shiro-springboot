package com.springboot.shiro.config;

import com.springboot.shiro.pojo.UserAuthority;
import com.springboot.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
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
        return null;
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
        UserAuthority user = userService.findByUsername(token.getUsername());
        if (user==null) {
            return null;//会抛出UnknownAccountException
        }
        //判断密码
        /**
         * arg1 返回的数据，arg2 数据库用户的密码，arg3 Shiro的名字
         */
        return new SimpleAuthenticationInfo("", user.getPassword(), "");
    }
}
