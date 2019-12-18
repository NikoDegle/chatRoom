package com.nciae.chatRoom.controller;

import com.nciae.chatRoom.service.InviteService;
import com.nciae.chatRoom.service.UserService;
import com.nciae.chatRoom.viewObject.FormUserVO;
import com.nciae.chatRoom.viewObject.UserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private UserService userServiceImpl;
    private InviteService inviteServiceImpl;

    @Autowired
    public LoginController(UserService userServiceImpl, InviteService inviteServiceImpl){
        this.userServiceImpl = userServiceImpl;
        this.inviteServiceImpl = inviteServiceImpl;
    }

    /**
     * 登录接口
     *
     * 用户输入用户名密码
     * 前端封装在requestbody中
     * 接口获取UserToken并传入userServiceImpl等待验证
     * 验证结果转换为String类型返回
     *
     * @param userToken
     * @return
     */
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

    /**
     * 用户接受邀请接口
     *
     * 用户直接点击url即可访问该接口
     * 该接口获取url中的uuid
     * 将uuid传入inviteServiceImpl进行验证
     * 成功则返回true 交由前端跳转注册页面
     * 失败返回false 前端跳转主页
     *
     * @param uuid
     * @return
     */
    @RequestMapping("/acceptInvite/{uuid}")
    public boolean acceptInvite(@PathVariable String uuid){
        return inviteServiceImpl.acceptInvite(uuid);
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
