package com.ljn.communityljn;

import com.ljn.communityljn.dao.DiscussPostMapper;
import com.ljn.communityljn.dao.LoginTicketMapper;
import com.ljn.communityljn.dao.UserMapper;
import com.ljn.communityljn.entity.DiscussPost;
import com.ljn.communityljn.entity.LoginTicket;
import com.ljn.communityljn.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @Author li
 * @Date 10/29/22 5:05 PM
 * @Version 1.0
 * 描述 ：
 * 名称：MapperTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes =CommunityLjnApplication.class)
public class MapperTest{


    @Autowired
    private LoginTicketMapper loginTicketMapper;



    @org.junit.Test
    public void testInsertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("abc", 1);
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }

}
