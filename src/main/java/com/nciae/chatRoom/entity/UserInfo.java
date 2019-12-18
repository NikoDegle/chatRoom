package com.nciae.chatRoom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class UserInfo {
    private Long id;
    private String userName;
    private String password;
    private String salt;
    private Integer shutUp;
    private String role;
}
