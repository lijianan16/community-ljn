package com.ljn.communityljn;

import com.ljn.communityljn.dao.DiscussPostMapper;
import com.ljn.communityljn.entity.DiscussPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author li
 * @Date 10/29/22 5:05 PM
 * @Version 1.0
 * 描述 ：
 * 名称：MapperTest
 */
public class MapperTest {
    @Autowired
  private   DiscussPostMapper discussPostMapper;

    @Test
    public void testselectposts(){
     List<DiscussPost> list= discussPostMapper.selectDiscussPosts(1,0,10);
     for (DiscussPost discussPost : list){
         System.out.println(discussPost);
     }
    }
}
