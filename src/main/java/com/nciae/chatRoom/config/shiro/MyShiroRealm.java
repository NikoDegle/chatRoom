package com.nciae.chatRoom.config.shiro;

import com.nciae.chatRoom.entity.UserInfo;
import com.nciae.chatRoom.mapper.UserMapper;
import com.nciae.chatRoom.service.RoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleServiceImpl;

    /**
     * 认证权限方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        System.out.println("获取权限");
        //获取用户信息
        UserInfo userInfo  = (UserInfo)principalCollection.getPrimaryPrincipal();

        //获取角色列表
        List<String> roleName = roleServiceImpl.getRoleNameList(userInfo.getRole());
        //添加角色信息 和 权限信息  这里因为只设计了角色  所以只添加角色
        for (String role : roleName){
            authorizationInfo.addRole(role);
//            System.out.println(role);
        }
        return authorizationInfo;
    }

    /**
     * 获取认证用户方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String)authenticationToken.getPrincipal();
        //根据用户名查询用户
//        System.out.println("查询用户");
        UserInfo userInfo = userMapper.selectUserByUserName(userName);
        if (null == userInfo){
            return null;
        }
        //用户权限  这里传入盐值 以待用户密码校验
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo,
                userInfo.getPassword(), ByteSource.Util.bytes(userInfo.getSalt()),
                userInfo.getId().toString());
//        System.out.println(userName);
        return authenticationInfo;
    }
}
