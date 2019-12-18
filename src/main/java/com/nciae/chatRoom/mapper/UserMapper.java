package com.nciae.chatRoom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nciae.chatRoom.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {

    @Select("select * from t_user where user_name = #{userName}")
    @Results({
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "id", column = "id"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "role", column = "role"),
            @Result(property = "shutUp", column = "shut_up")
    })
    UserInfo selectUserByUserName(String userName);

    @Select("select * from t_user")
    List<UserInfo> selectAll();
}
