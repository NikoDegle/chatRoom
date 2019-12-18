package com.nciae.chatRoom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nciae.chatRoom.entity.InviteInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface InviteMapper extends BaseMapper<InviteInfo> {

    @Select("select * from t_invite where uuid = #{uuid}")
    InviteInfo selectInviteByUUID(String uuid);
}
