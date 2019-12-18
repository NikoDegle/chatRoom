package com.nciae.chatRoom.controller;

import com.nciae.chatRoom.service.UserService;
import com.nciae.chatRoom.viewObject.FormUserVO;
import com.nciae.chatRoom.viewObject.UserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserService userServiceImpl;

    @PostMapping("/login")
    public String login(@RequestBody UserToken userToken){
        System.out.println("登录验证");
        return userServiceImpl.login(userToken);
    }

    @PostMapping("/regist")
    public boolean regist(@RequestBody FormUserVO formUserVO){
        boolean result = false;
        try {
            result = userServiceImpl.saveUser(formUserVO);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return result;
    }

    //用户登出
    @GetMapping("/logout")
    public boolean logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return true;
    }

    @RequestMapping("/noAuthentication")
    public String haveNoAuthentication(){
        return "您没有权限";
    }

    @GetMapping("/getCurrentUser")
    public String getCurrentUser(){
        return userServiceImpl.getCurrentUserName();
    }
}
