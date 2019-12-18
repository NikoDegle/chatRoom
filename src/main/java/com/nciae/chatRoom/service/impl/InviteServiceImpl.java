package com.nciae.chatRoom.service.impl;

import com.nciae.chatRoom.entity.InviteInfo;
import com.nciae.chatRoom.entity.UserInfo;
import com.nciae.chatRoom.mapper.InviteMapper;
import com.nciae.chatRoom.service.InviteService;
import com.nciae.chatRoom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author : 王翰聪
 * @version 1.0
 *
 * 邀请服务模块，目前包括接受邀请和创建邀请两个功能
 *
 */

@Service
public class InviteServiceImpl implements InviteService {
    private InviteMapper inviteMapper;
    private UserService userServiceImpl;

    private static final Integer CANNOT_BE_USED = 1;
    private static final Integer CAN_BE_USED = 0;

    /**
     * set方法自动注入
     * @param inviteMapper
     */
    @Autowired
    public void setInviteMapper(InviteMapper inviteMapper){
        this.inviteMapper = inviteMapper;
    }

    /**
     * 构造方法自动注入
     * @param userServiceImpl
     */
    @Autowired
    public InviteServiceImpl(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    /**
     * 接受邀请，传入邀请的uuid
     * 如果该邀请有效，则使其失效并返回true
     * 如果邀请失效，则返回false
     * @param uuid
     */
    @Override
    public boolean acceptInvite(String uuid) {
        //根据uuid查询邀请
        InviteInfo inviteInfo = inviteMapper.selectInviteByUUID(uuid);
        if (null == inviteInfo){
            return false;
        }

        Date now = new Date();
        //邀请时间过了五天以上
        if (inviteInfo.getCreateTime().getTime() - now.getTime() > 432000000L){
            return false;
        }

        //该邀请已经被使用
        if (inviteInfo.getBeUsed() == CANNOT_BE_USED.intValue()){
            return false;
        }

        //该邀请是有效的  设置为无效并存入库
        inviteInfo.setBeUsed(CANNOT_BE_USED);
        inviteMapper.updateById(inviteInfo);
        return true;
    }

    /**
     * 创建邀请方法
     *
     * 获取当前用户id，作为邀请人id
     * 设置创建时间为当前时间
     * 设置邀请状态为可以使用
     * 设置uuid作为接受邀请时的凭证
     *
     * @return
     */
    @Override
    public String createInvite() {
        //获取当前用户id
        Long userid = userServiceImpl.getCurrentUserId();
        InviteInfo inviteInfo = new InviteInfo();
        //设置邀请人
        inviteInfo.setUser(userid);
        inviteInfo.setCreateTime(new Date());
        inviteInfo.setBeUsed(CAN_BE_USED);
        //设置uuid
        inviteInfo.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));

        //存库
        inviteMapper.insert(inviteInfo);
        //返回uuid供接口拼接
        return inviteInfo.getUuid();
    }
}
