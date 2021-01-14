package com.venns.demo04;

import com.venns.demo02.UserService;
import com.venns.demo02.UserServiceImpl;

public class Client {
    public static void main(String[] args) {
        //真实角色
        UserServiceImpl userService = new UserServiceImpl();

        //生成代理角色
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setObj(userService);//设置需要代理的角色
        UserService proxy = (UserService) pih.getProxy();

        //执行方法
        proxy.add();
    }
}
