package com.ljn.communityljn.controller;

import com.ljn.communityljn.entity.DiscussPost;
import com.ljn.communityljn.entity.Page;
import com.ljn.communityljn.entity.User;
import com.ljn.communityljn.service.DiscussPostService;
import com.ljn.communityljn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author li
 * @Date 10/31/22 3:22 PM
 * @Version 1.0
 * 描述 ：主页视图层
 * 名称：HomeController
 */
@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path= "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        //方法调用栈，springmvc会自动实例化Model和page，并将Page注入Model
        //所以,在thymeleaf中可以直接访问Page对象中的数据
        page.setRows(discussPostService.findDiscussPostRows(103));
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(103,page.getOffset(),page.getLimit());

        List<Map<String,Object>> discussPosts = new ArrayList<>();
        if (list!=null){
            for (DiscussPost discussPost:list){
                System.out.println(discussPost);
                Map<String,Object> map = new HashMap<>();
                map.put("post",discussPost);
                User user = userService.findUserById(discussPost.getUserId());

                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "index";
    }



}
