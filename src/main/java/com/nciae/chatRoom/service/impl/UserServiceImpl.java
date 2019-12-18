package com.nciae.chatRoom.service.impl;

import com.nciae.chatRoom.entity.UserInfo;
import com.nciae.chatRoom.mapper.UserMapper;
import com.nciae.chatRoom.service.UserService;
import com.nciae.chatRoom.tools.SecurityTools;
import com.nciae.chatRoom.viewObject.FormUserVO;
import com.nciae.chatRoom.viewObject.UserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 用户注册方法
     * @param userVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveUser(FormUserVO userVO) throws Exception {
        UserInfo userInfo = null;
        userInfo = userMapper.selectUserByUserName(userVO.getUserName());
        if (null != userInfo){
            throw new Exception("用户名已存在");
        }
        //用户名
        userInfo = new UserInfo();
        userInfo.setUserName(userVO.getUserName());
        //密码
        String salt = UUID.randomUUID().toString().replace("-", "");
        String password = SecurityTools.md5(userVO.getPassword(), salt);
        userInfo.setPassword(password);
        userInfo.setSalt(salt);

        userInfo.setShutUp(0);
        userInfo.setRole("1");

        userMapper.insert(userInfo);
        return true;
    }

    /**
     * 用户登录方法
     *
     * 获取从shiro中传入的UserToken
     * 根据UserToken的用户名从库中查询用户信息userInfo
     * 从userInfo中获取盐和密码
     * 获取shiroSession中的subject对象，调用该对象login方法登录
     *
     * login方法可能抛出异常
     * UnknownAccountException:帐号不存在异常
     * IncorrectCredentialsException:密码不正确异常
     * 其他异常
     * 抛出异常则为登录失败
     *
     * @param userToken
     * @return
     */
    @Override
    public String login(UserToken userToken) {
//        System.out.println("用户登录");
        //获取用户信息
        UserInfo userInfo = userMapper.selectUserByUserName(userToken.getUserName());
        String salt = userInfo.getSalt();
        //用户登录
        Subject currentUser = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(userToken.getUserName(),
                SecurityTools.md5(userToken.getPassword(), salt));
        try {
            currentUser.login(token);
        } catch (UnknownAccountException e){
            return "账号不存在：";
        } catch (IncorrectCredentialsException e){
            return "密码不正确：";
        } catch (Exception e){
            return "登录失败";
        }
        return "登录成功";
    }

    /**
     * 返回当前用户名方法
     *
     * 从shiro框架中获取当前用户
     * 返回用户的用户名
     *
     * @return 当前用户用户名
     */
    @Override
    public String getCurrentUserName() {
        UserInfo userInfo = (UserInfo)SecurityUtils.getSubject().getPrincipal();
        return userInfo.getUserName();
    }

    /**
     * 返回当前登录用户id方法
     *
     * 从shiro框架中获取当前用户
     * 返回用户id
     *
     * @return
     */
    @Override
    public Long getCurrentUserId() {
        UserInfo userInfo = (UserInfo)SecurityUtils.getSubject().getPrincipal();
        return userInfo.getId();
    }
}
