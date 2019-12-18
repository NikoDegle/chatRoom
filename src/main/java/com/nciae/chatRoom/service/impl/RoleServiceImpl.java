package com.nciae.chatRoom.service.impl;

import com.nciae.chatRoom.entity.RoleInfo;
import com.nciae.chatRoom.mapper.RoleMapper;
import com.nciae.chatRoom.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<String> getRoleNameList(String role) {
        List<String> roleNameList = new ArrayList<>();

        String roleIds[] = role.split(",");
        for (String id : roleIds){
            RoleInfo roleInfo = roleMapper.selectById(Long.parseLong(id));
            roleNameList.add(roleInfo.getRoleName());
        }
        return roleNameList;
    }
}
