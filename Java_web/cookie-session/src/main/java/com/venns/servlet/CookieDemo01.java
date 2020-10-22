package com.venns.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

//保存用户上一次访问的时间
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器告诉你你来的时间，把这个时间封装成一个信件，下次访问带来就行了。

        //解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();

        //Cookie,服务器端口从客户端获取
        Cookie[] cookies = req.getCookies();//返回数组，表明cookie有多个

        //判断cookie是否存在
        if (cookies != null){
            //如果存在怎么办
            out.print("你上一次访问的时间是");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                //获取cookie的名字
                if (cookie.getName().equals("lastLoginTime")){
                    //获取cookie的值
                    long lastLoginTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastLoginTime);
                    out.write(date.toLocaleString());
                }

            }
        }else {
            out.print("这是您第一次访问网站");
        }
        //服务器给客户端响应一个cookie
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis() + "");
        cookie.setMaxAge(60*60);
        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
