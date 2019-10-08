package com.springboot.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Shiro配置类
 *
 * @author wangwei
 * @version 1.0
 * @date 2019/10/8 14:19
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean 权限过滤工厂bean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * 添加Shiro内置过滤器，可以实现权限相关的拦截器
         *      常用过滤器
         *          anon:无需认证(登录)可以访问
         *          authc:必须认证才可以访问
         *          user:如果使用rememberMe功能可以直接访问
         *          perms:该资源必须得到资源权限，才可以访问
         *          role:该资源必须得到角色权限，才可以访问
         */
        HashMap<String, String> filterMap = new LinkedHashMap<>();
       /* filterMap.put("/user/add","authc");
        filterMap.put("/user/update","authc");*/
        filterMap.put("/user/hello", "anon");//无需认证的写在前面
        filterMap.put("/user/login", "anon");//无需认证的写在前面
        //设置授权过滤器
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");

        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //修改调整登录页面
        shiroFilterFactoryBean.setLoginUrl("/user/toLogin");
        //设置为授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/unauthorized");
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager 默认web安全管理
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建realm 用户领域
     */
    @Bean
    @Qualifier("userRealm")
    public UserRealm getUserRealm() {
        return new UserRealm();
    }

    /**
     * 配置ShiroDialect ,用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
