package com.springboot.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("hello")
    public String hello(Model model) {
        model.addAttribute("name", "我来啦！！！");
        return "test";
    }

    @RequestMapping("add")
    public String add() {
        return "users/add";
    }

    @RequestMapping("update")
    public String update() {
        return "users/update";
    }

    @RequestMapping("toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * Shiro登录逻辑
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("login")
    public String login(String username, String password,Model model) {
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //3.执行登录方法
        try {
            subject.login(token);
            return "redirect:/user/hello";
        } catch (UnknownAccountException e) {
            //登录失败：用户名不存在
            model.addAttribute("msg","登录失败：用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            //登录失败：密码错误
            model.addAttribute("msg","登录失败：密码错误");
            return "login";
        }
    }
}
