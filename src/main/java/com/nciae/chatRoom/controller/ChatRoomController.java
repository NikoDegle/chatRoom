package com.nciae.chatRoom.controller;

import com.nciae.chatRoom.entity.Message;
import com.nciae.chatRoom.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ChatRoomController {
    @Autowired
    private ChatRoomService chatRoomServiceImpl;

    /**
     * 用户加入指定聊天室方法
     * @param chatRoomNum
     * @return
     */
    @GetMapping("/join/{chatRoomNum}")
    public boolean joinChatRoom(@PathVariable Integer chatRoomNum){
        return chatRoomServiceImpl.intoChatRoom(chatRoomNum);
    }

    /**
     * 用户发送聊天消息方法
     * @param content
     * @return
     */
    @GetMapping("/speak")
    public boolean speak(@RequestParam String content){
        return chatRoomServiceImpl.userSpeak(content);
    }

    /**
     * 用户退出聊天室方法
     */
    @GetMapping("/exit")
    public void exit(){
        chatRoomServiceImpl.exitChatRoom();
    }

    /**
     * 获取聊天室内消息列表方法
     * @return
     */
    @GetMapping("/getMessageList")
    public List<Message> getMessageList(){
        return chatRoomServiceImpl.getMessageList();
    }

    /**
     * 获取开放的聊天室列表方法
     * @return
     */
    @GetMapping("/getChatRoomNumList")
    public List<Integer> getChatRoomNumList(){
        return chatRoomServiceImpl.getChatRoomNumList();
    }
}
