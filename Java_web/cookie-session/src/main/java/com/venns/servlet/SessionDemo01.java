package com.venns.servlet;

import com.venns.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码问题
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //得到Session
        HttpSession session = req.getSession();
        session.setAttribute("name", new Person("venns",18));

        //获取Session的id
        String id = session.getId();

        //判断Session是不是新创建的
        if (session.isNew()) {
            resp.getWriter().write("Session创建成功 id:"+id);
        }else {
            resp.getWriter().write("Session已经存在,id"+id);
        }

        //Session创建的时候做了什么事情
//        Cookie jsessionid = new Cookie("JSESSIONID", id);
//        resp.addCookie(jsessionid);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
