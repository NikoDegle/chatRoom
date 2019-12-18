package com.nciae.chatRoom.service;

public interface InviteService {

    /**
     * 用户接受邀请
     */
    boolean acceptInvite(String uuid);

    /**
     * 用户创建邀请
     * @return 唯一uuid作为该次邀请的凭证
     */
    String createInvite();
}
