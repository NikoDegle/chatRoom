package com.nciae.chatRoom.service;

import com.nciae.chatRoom.viewObject.FormUserVO;
import com.nciae.chatRoom.viewObject.UserToken;

public interface UserService {

    /**
     * 用户注册方法
     * @param user
     * @return
     */
    boolean saveUser(FormUserVO user) throws Exception;

    /**
     * 用户登录方法
     * @param userToken
     * @return
     */
    String login(UserToken userToken);

    /**
     * 获取当前登录用户用户名方法
     * @return
     */
    String getCurrentUserName();

    /**
     * 获取当前登录用户id方法
     * @return
     */
    Long getCurrentUserId();
}
