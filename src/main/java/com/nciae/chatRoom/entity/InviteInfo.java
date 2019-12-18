package com.nciae.chatRoom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_invite")
public class InviteInfo {

    private Long id;
    private Long user;
    private Date createTime;
    private String uuid;

    /**
     * 该邀请被使用过了  0为未被使用 1为被使用
     */
    private Integer beUsed;
}
