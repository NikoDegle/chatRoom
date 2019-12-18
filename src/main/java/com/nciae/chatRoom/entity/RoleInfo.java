package com.nciae.chatRoom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_role")
public class RoleInfo {
    private Long id;
    private String roleName;
}
