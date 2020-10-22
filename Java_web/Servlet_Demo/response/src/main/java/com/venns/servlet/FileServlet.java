package com.venns.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取下载文件的路径
        String realPath = this.getServletContext().getRealPath("/logo.png");
        System.out.println("下载文件的路径"+realPath);
        //2.下载文件名是什么
        String fileName = realPath.substring(realPath.lastIndexOf("//") + 1);
        //3.设置想办法让浏览器能够支持(content-disposition) 下载我们需要的东西,中文文件名需要用URLEncoder.encode解决乱码
        resp.setHeader("content-disposition","attachment;filename"+ URLEncoder.encode(fileName,"UTF-8"));
        //4.获取下载文件的输入流
        FileInputStream in = new FileInputStream(realPath);
        //5.创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
        //6.获取输出流对象
        ServletOutputStream out = resp.getOutputStream();
        //7.将FileOutputStream流写入buffer缓冲区,使用OutputStream将缓冲区的数据输出到客户端
        while((len = in.read(buffer)) > 0){
            out.write(buffer,0,len);
        }
        in.close();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
