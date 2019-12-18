package com.nciae.chatRoom.entity;

import com.nciae.chatRoom.entity.mainObjectInter.AbstractChatRoom;
import com.nciae.chatRoom.entity.mainObjectInter.AbstractChatRoomPool;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class PublicChatRoomPool extends AbstractChatRoomPool<PublicChatRoom> {

    @Override
    public void init() {
        this.chatRoomMap = new LinkedHashMap<>();
        this.MAX_CHATROOM_NUM = 10;
    }

    /**
     * 创建聊天室方法
     * @return
     */
    @Override
    public boolean createChatRoom() {
        int num = chatRoomMap.size() + 1;
        if (num > MAX_CHATROOM_NUM){
            return false;
        }
        chatRoomMap.put(num, new PublicChatRoom());
        chatRoomMap.get(num).init();
        return true;
    }

    /**
     * 删除指定聊天室方法
     * @param target
     * @return
     */
    @Override
    public boolean dropChatRoom(Integer target) {
        AbstractChatRoom abstractChatRoom = null;
        abstractChatRoom = chatRoomMap.remove(target);
        if (abstractChatRoom == null){
            return true;
        }
        return false;
    }
}
