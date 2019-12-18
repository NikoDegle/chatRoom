package com.nciae.chatRoom.controller;

import com.nciae.chatRoom.service.InviteService;
import com.nciae.chatRoom.service.impl.InviteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inviter")
public class InviteController {

    private InviteService inviteServiceImpl;

    @Autowired
    public void setInviteServiceImpl(InviteServiceImpl inviteServiceImpl){
        this.inviteServiceImpl = inviteServiceImpl;
    }

    /**
     * 返回用户可以点击的链接  用于用户接受邀请
     *
     * 获取当前项目目录
     * 拼接接受邀请页面地址
     * 拼接uuid
     *
     * @return
     */
    @GetMapping("/createInvite")
    public String createInvite(){
        return System.getProperty("user.dir") + "/acceptInvite/" + inviteServiceImpl.createInvite();
    }
}
