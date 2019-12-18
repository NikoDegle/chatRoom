package com.nciae.chatRoom.controller;

import com.nciae.chatRoom.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ChatRoomService chatRoomServiceImpl;

    /**
     * 管理员开放一个聊天室方法
     * @return
     */
    @GetMapping("/createChatRoom")
    public boolean createChatRoom(){
        return chatRoomServiceImpl.createChatRoom();
    }

    /**
     * 管理员关闭一个聊天室方法
     * @param chatRoomNum
     * @return
     */
    @GetMapping("/dropChatRoom/{chatRoomNum}")
    public boolean dropChatRoom(@PathVariable Integer chatRoomNum){
        return chatRoomServiceImpl.dropChatRoom(chatRoomNum);
    }
}
