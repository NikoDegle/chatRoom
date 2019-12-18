package com.nciae.chatRoom.service;

import com.nciae.chatRoom.entity.Message;

import java.util.List;

public interface ChatRoomService {
    /**
     * 用户进入指定编号聊天室方法
     * @param chatRoomNum
     * @return
     */
    boolean intoChatRoom(Integer chatRoomNum);

    /**
     * 用户发言方法
     * @param content
     * @return
     */
    boolean userSpeak(String content);

    /**
     * 用户退出聊天室方法
     * @return
     */
    void exitChatRoom();

    /**
     * 用户浏览消息记录方法
     * @return
     */
    List<Message> getMessageList();

    /**
     * 获取开放的聊天室编号方法
     * @return
     */
    List<Integer> getChatRoomNumList();

    /**
     * 开放一个新的聊天室方法
     * @return
     */
    boolean createChatRoom();

    /**
     * 关闭一个聊天室方法
     * @param chatRoomNum
     * @return
     */
    boolean dropChatRoom(Integer chatRoomNum);
}
