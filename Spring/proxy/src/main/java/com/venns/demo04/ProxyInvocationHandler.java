package com.venns.demo04;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private Object obj;

    //得到被代理的接口
    public void setObj(Object obj){
        this.obj = obj;
    }

    //生成得到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), obj.getClass().getInterfaces(),this);
    }

    //处理代理实例
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log(method.getName());
        Object result = method.invoke(obj, args);
        return result;
    }
    private void log(String msg){
        System.out.println("执行了"+msg+"方法");
    }
}
