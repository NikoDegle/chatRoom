package com.nciae.chatRoom.entity.mainObjectInter;

import com.nciae.chatRoom.entity.Message;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 聊天室抽象类
 */
@Data
public abstract class AbstractChatRoom {
    //用户列表
    protected List<String> userList;

    //消息列表  integer为消息编号，新的消息覆盖最老的消息
    protected Map<Integer, Message> messageMap;

    protected Integer MAX_USER_NUM = 10;

    protected Integer MAX_MESSAGE_NUM = 100;

    public abstract void init();

    //查看用户是否在该聊天室中
    public boolean userInChatRoom(String userName){
        return userList.contains(userName);
    }

    //查询用户是否满员
    public boolean maxUser(){
        return userList.size() == MAX_USER_NUM.intValue();
    }

    //查询聊天室中有没有用户
    public boolean isEmpty(){
        return userList.isEmpty();
    }

    //添加用户至聊天室  用户超过用户数量上限则返回失败
    public boolean userIntoChatRoom(String userName){
        if (userList.size() <= this.MAX_USER_NUM){
            userList.add(userName);
            return true;
        }
        return false;
    }

    //用户退出聊天室
    public void quit(String userName){
        userList.remove(userName);
    }

    //用户发送消息
    public abstract boolean speak(String userName, String content);

    //用户读取聊天室信息
    public abstract List<Message> getMessageList();

    //用户读取目标条数聊天室信息
    public abstract List<Message> getMessageList(int start, int end);

    //用户读取指定消息
    public abstract Message getMessage(int num);

    //清空聊天室消息列表
    public void clearMessage(){
        messageMap.clear();
    }

    //清空聊天室所有用户
    public void kickAllUser(){
        userList.clear();
    }

    //踢出聊天室
    public void kickUser(String userName){
        userList.remove(userName);
    }
}
