package com.venns;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot08TaskApplicationTests {
    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    void contextLoads() {
        //一个简单的邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("邮件标题");
        message.setText("邮件内容");
        message.setTo("");//接收者邮箱
        message.setFrom("");//发送者邮箱
        javaMailSender.send(message);
    }

    @Test
    void contextLoads2() throws MessagingException {
        //一个复杂的邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");
        helper.setSubject("邮件标题");
        helper.setText("邮件内容",true);//true表示会转义为html
        helper.addAttachment("1.jpg",new File("")); //附件
        helper.setTo("1752877226@qq.com"); //接收者邮箱
        helper.setFrom("2396177829@qq.com"); //发送者邮箱
        javaMailSender.send(mimeMessage);
    }

}
