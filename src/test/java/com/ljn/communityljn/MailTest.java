package com.ljn.communityljn;

import com.ljn.communityljn.utils.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @Author li
 * @Date 11/1/22 10:18 AM
 * @Version 1.0
 * 描述 ：
 * 名称：MailTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityLjnApplication.class)
public class MailTest {

    @Autowired(required = true)
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail(){
     //   MailClient mailClient = new MailClient();
        mailClient.sendMail("774715820@qq.com","TEST","Welcome.");
    }

    @Test
    public void testHtmlMail(){
        Context context = new Context();
        context.setVariable("username","sunday");

        String content = templateEngine.process("/mail/demo",context);
        mailClient.sendMail("774715820@qq.com","HTML",content);

    }
}
