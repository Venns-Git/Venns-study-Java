package com.venns.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//统计网络在线人数：统计session
public class OnlineCountListener implements HttpSessionListener {
    //创建session监听
    //一旦创建session，就会触发一个这个事件
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Integer onlineCount = (Integer) servletContext.getAttribute("OnlineCount");
        if (onlineCount == null){
            onlineCount = 1;
        }else {
            int count = onlineCount;
            onlineCount = count + 1;
        }
        servletContext.setAttribute("OnlineCount",onlineCount);
    }
    //销毁session监听
    //一旦销毁session，就会触发一个这个事件
    /*
        session销毁：
            1.手动销毁：httpSessionEvent.getSession().invalidate();
            2.自动销毁：web.xml中设置session存活时间
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Integer onlineCount = (Integer) servletContext.getAttribute("OnlineCount");
        System.out.println(httpSessionEvent.getSession().getId());
        if (onlineCount == null){
            onlineCount = 0;
        }else {
            int count = onlineCount;
            onlineCount = count - 1;
        }
        servletContext.setAttribute("OnlineCount",onlineCount);
    }
}
