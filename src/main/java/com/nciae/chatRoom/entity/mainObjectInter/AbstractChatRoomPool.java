package com.nciae.chatRoom.entity.mainObjectInter;

import lombok.Data;

import java.util.Map;

/**
 * 聊天室池抽象类
 */
@Data
public abstract class AbstractChatRoomPool<T> {
    //聊天室列表
    protected Map<Integer, AbstractChatRoom> chatRoomMap;
    //最大聊天室数量
    protected Integer MAX_CHATROOM_NUM = 10;

    //初始化聊天室池方法
    public abstract void init();

    //创建聊天室方法
    public abstract boolean createChatRoom();

    //删除目标聊天室
    public abstract boolean dropChatRoom(Integer target);

    //获取池中的聊天室数量
    public Integer getChatRoomNum(){
        return chatRoomMap.size();
    }

    //获取池中是否存在聊天室
    public boolean isEmpty(){
        return chatRoomMap.isEmpty();
    }

    //获取池中的指定聊天室
    public T getChatRoom(Integer chatRoomNum){
        return (T) chatRoomMap.get(chatRoomNum);
    }
}
