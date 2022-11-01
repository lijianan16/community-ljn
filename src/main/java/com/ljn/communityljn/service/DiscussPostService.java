package com.ljn.communityljn.service;

import com.ljn.communityljn.dao.DiscussPostMapper;
import com.ljn.communityljn.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author li
 * @Date 10/29/22 5:19 PM
 * @Version 1.0
 * 描述 ：
 * 名称：DiscussPostService
 */
@Service
public class DiscussPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int userId,int offset,int limit){
        return discussPostMapper.selectDiscussPosts(userId,offset,limit);
    }

    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }

}
