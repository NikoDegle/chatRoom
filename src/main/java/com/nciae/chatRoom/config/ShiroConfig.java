package com.nciae.chatRoom.config;

import com.nciae.chatRoom.config.shiro.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        //创建shiro工厂类
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //创建shiro安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //创建shiro拦截器链  注意这里使用LinkedHashMap有序map
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置拦截页面
        //authc为需要权限认证  anon为不需要权限认证  roles[aaa]即需要有aaa的角色才能访问
        //perms[bbb]为需要有bbb的权限才能访问 roles[a], perms[b]为需要有a的角色和b的权限才能访问
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        filterChainDefinitionMap.put("/inviter/**", "roles[inviter]");
        filterChainDefinitionMap.put("/user/**", "roles[user]");
        //登出必须在登录之后，即具有权限的情况下
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**", "anon");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuthentication");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }


    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }
}
