package com.nciae.chatRoom.entity;

import com.nciae.chatRoom.entity.mainObjectInter.AbstractChatRoom;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 继承AbstractChatRoom后  只重写消息有关的方法
 */
public class PublicChatRoom extends AbstractChatRoom {

    @Override
    public void init() {
        this.userList = new ArrayList<>();
        this.messageMap = new LinkedHashMap<>();
        this.MAX_USER_NUM = 10;
        this.MAX_MESSAGE_NUM = 100;
    }

    /**
     * 用户说话方法
     * @param userName
     * @param content
     * @return
     */
    @Override
    public boolean speak(String userName, String content) {
        //如果用户不在当前聊天室则返回false
        if (!userInChatRoom(userName)){
            return false;
        }
        //如果消息数量到达消息上限则覆盖最老的消息
        messageMap.put(((messageMap.size() + 1)%MAX_MESSAGE_NUM), new Message(userName, content));
        return true;
    }

    /**
     * 用户进入聊天室的时候获取所有聊天室中的信息方法
     * @return
     */
    @Override
    public List<Message> getMessageList() {
        List<Message> messageList = new ArrayList<>();
        for (Integer id : messageMap.keySet()){
            messageList.add(messageMap.get(id));
        }
        return messageList;
    }

    /**
     * 该方法暂时不使用
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<Message> getMessageList(int start, int end) {
        return null;
    }

    /**
     * 获取单个信息方法
     * @param num
     * @return
     */
    @Override
    public Message getMessage(int num) {
        return messageMap.get(num);
    }
}
