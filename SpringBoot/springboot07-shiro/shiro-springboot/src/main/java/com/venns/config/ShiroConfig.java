package com.venns.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    //第三步：ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
            anon：无需认证就可访问
            authc：必须认证了才能访问
            user：必须拥有 记住我 才能用
            perms： 拥有对某个资源的权限才能访问
            role：拥有某个角色权限才能访问
         */
        HashMap<String, String> filterMap = new LinkedHashMap<>();

        /*
            授权
            表示有user这个角色，并且有add权限才能访问
            正常情况下，没有授权会跳转到未授权页面
         */
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("user/update","perms[user:update]");
        filterMap.put("/user/*","authc");
        factoryBean.setFilterChainDefinitionMap(filterMap);

        //设置登录请求
        factoryBean.setLoginUrl("/toLogin");
        //设置未授权页面
        factoryBean.setUnauthorizedUrl("/noauth");

        return factoryBean;
    }


    //第二步：DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    //第一步：创建 Realm 对象，需要自定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }



}
