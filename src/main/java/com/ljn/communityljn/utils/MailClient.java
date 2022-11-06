package com.ljn.communityljn.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author li
 * @Date 11/1/22 10:11 AM
 * @Version 1.0
 * 描述 ：邮箱客户端
 * 名称：MailClient
 */
@Component
public class MailClient {
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    @Autowired
    private JavaMailSender mailSender;



  //  @Value("${spring.mail.username}")
   private String from = "lijianan0126@sina.com";

    public void sendMail(String to, String subject, String content) {
        System.out.println(mailSender);
        try {
          //  System.out.println(from);
            MimeMessage message = mailSender.createMimeMessage();
            //System.out.println(message);
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("发送邮件失败:" + e.getMessage());
        }
    }

//    public static void main(String[] args) {
//        MailClient mailClient = new MailClient();
//        mailClient.sendMail("774715820@qq.com","TEST","Welcome.");
//    }

}
