package com.nciae.chatRoom.service.impl;

import com.nciae.chatRoom.entity.Message;
import com.nciae.chatRoom.entity.PublicChatRoom;
import com.nciae.chatRoom.entity.PublicChatRoomPool;
import com.nciae.chatRoom.service.ChatRoomService;
import com.nciae.chatRoom.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    private PublicChatRoomPool publicChatRoomPool;
    @Autowired
    private UserService userServiceImpl;

    /**
     * 获取当前用户所在聊天室方法
     * @return
     */
    public PublicChatRoom getUserChatRoom(){
        //获取当前用户所在聊天室编号
        Integer chatRoomNum = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userRoomNum");
        return publicChatRoomPool.getChatRoom(chatRoomNum);
    }

    /**
     * 将用户添加进聊天室方法
     * @param chatRoomNum
     * @return
     */
    @Override
    public boolean intoChatRoom(Integer chatRoomNum) {
        //获取当前用户名
        String userName = userServiceImpl.getCurrentUserName();
        //尝试将当前用户加入聊天室
        boolean result = publicChatRoomPool.getChatRoom(chatRoomNum).userIntoChatRoom(userName);
        if (result){
            SecurityUtils.getSubject().getSession().setAttribute("userRoomNum", chatRoomNum);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 用户发送消息方法
     * @param content
     * @return
     */
    @Override
    public boolean userSpeak(String content) {
        //获取当前用户所在聊天室
        PublicChatRoom publicChatRoom = getUserChatRoom();
        return publicChatRoom.speak(userServiceImpl.getCurrentUserName(), content);
    }

    /**
     * 用户退出聊天室方法
     */
    @Override
    public void exitChatRoom() {
        //获取当前用户所在聊天室
        PublicChatRoom publicChatRoom = getUserChatRoom();
        publicChatRoom.quit(userServiceImpl.getCurrentUserName());
        SecurityUtils.getSubject().getSession().removeAttribute("userRoomNum");
    }

    /**
     * 用户获取聊天记录列表方法
     * @return
     */
    @Override
    public List<Message> getMessageList() {
        return getUserChatRoom().getMessageList();
    }

    /**
     * 获取开放的聊天室编号列表方法
     * @return
     */
    @Override
    public List<Integer> getChatRoomNumList() {
        List<Integer> chatRoomNumList = new ArrayList<>();
        for (Integer key : publicChatRoomPool.getChatRoomMap().keySet()){
            chatRoomNumList.add(key);
        }
        return chatRoomNumList;
    }

    /**
     * 创建一个新的聊天室方法
     * @return
     */
    @Override
    public boolean createChatRoom() {
        //如果聊天室池没有初始化 则初始化
        if (null == publicChatRoomPool.getChatRoomMap() || publicChatRoomPool.getChatRoomMap().isEmpty()){
            publicChatRoomPool.init();
        }
        return publicChatRoomPool.createChatRoom();
    }

    /**
     * 关闭指定聊天室方法
     * @param chatRoomNum
     * @return
     */
    @Override
    public boolean dropChatRoom(Integer chatRoomNum) {
        return publicChatRoomPool.dropChatRoom(chatRoomNum);
    }
}
