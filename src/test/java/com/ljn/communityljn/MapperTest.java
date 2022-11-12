package com.ljn.communityljn;

import com.ljn.communityljn.dao.LoginTicketMapper;
import com.ljn.communityljn.entity.LoginTicket;
import com.ljn.communityljn.utils.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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

    @Autowired
    private SensitiveFilter sensitiveFilter;
    @Autowired
    private RedisTemplate redisTemplate;

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

    @Test
    public void testsensitive(){
        String text = "这里可以嫖娼，赌博，哈哈哈";
        text = sensitiveFilter.filter(text);
        System.out.println(text);
    }

    @Test
    public void testStrings(){
        String redisKey = "test:count";
        redisTemplate.opsForValue().set(redisKey,1);


        System.out.println(redisTemplate.opsForValue().get(redisKey));
        System.out.println(redisTemplate.opsForValue().increment(redisKey));
//        System.out.println(redisTemplate.opsForValue().decrement(redisKey));
    }

    @Test
    public void testHashes(){
        String key = "test:user";

        redisTemplate.opsForHash().put(key,"id","1");
        redisTemplate.opsForHash().put(key,"username","zhangsan");

        System.out.println(redisTemplate.opsForHash().get(key,"id"));
        System.out.println(redisTemplate.opsForHash().get(key,"username"));
    }
    @Test
    public void testLists(){
        String redisKey = "test:ids";

        redisTemplate.opsForList().leftPush(redisKey,101);
        redisTemplate.opsForList().leftPush(redisKey,102);
        redisTemplate.opsForList().leftPush(redisKey,103);

        System.out.println(redisTemplate.opsForList().size(redisKey));
        System.out.println(redisTemplate.opsForList().index(redisKey,0));
        System.out.println(redisTemplate.opsForList().range(redisKey,0,2));

        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
    }

    @Test
    public void testSets(){
        String rediskey = "test:teachers";

        redisTemplate.opsForSet().add(rediskey,"liubei","guanyu");

        System.out.println(redisTemplate.opsForSet().size(rediskey));
        System.out.println(redisTemplate.opsForSet().pop(rediskey));
        System.out.println(redisTemplate.opsForSet().members(rediskey));
    }
    @Test
    public void testSortedSets(){
        String redisKey = "test:students";

        redisTemplate.opsForZSet().add(redisKey,"tangsen",80);
        redisTemplate.opsForZSet().add(redisKey,"wuko",90);
        redisTemplate.opsForZSet().add(redisKey,"shaseng",70);
        redisTemplate.opsForZSet().add(redisKey,"bajie",30);
        redisTemplate.opsForZSet().add(redisKey,"balongma",60);

        System.out.println(redisTemplate.opsForZSet().zCard(redisKey));
        System.out.println(redisTemplate.opsForZSet().score(redisKey,"bajie"));
        System.out.println(redisTemplate.opsForZSet().reverseRank(redisKey,"bajie"));
        System.out.println(redisTemplate.opsForZSet().reverseRange(redisKey,0,2));

    }



}
