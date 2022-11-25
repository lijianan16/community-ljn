package com.ljn.communityljn.config;

import com.ljn.communityljn.utils.CommunityConstant;
import com.ljn.communityljn.utils.CommunityUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author li
 * @Date 11/16/22 9:37 AM
 * @Version 1.0
 * 描述 ：security配置类
 * 名称：SecurityConfig
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements CommunityConstant {

    //忽略掉静态资源
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    //认证要绕过security的认证


    //进行授权的配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //授权
        //拦截的路径
        http.authorizeRequests()
                .antMatchers("/user/setting",
                        "/user/upload",
                        "/discuss/add",
                        "/comment/add/**",
                        "/letter/**",
                        "/notice/**",
                        "/like",
                        "/follow",
                        "/unfollow"
                )
                .hasAnyAuthority(
                        AUTHORITY_USER,
                        AUTHORITY_MODERATOR,
                        AUTHORITY_ADMIN
                )
                .antMatchers(
                        "/discuss/top",
                        "/discuss/wonderful"
                )
                .hasAnyAuthority(
                        //版主可以访问这两个权限
                        AUTHORITY_MODERATOR
                )
                .antMatchers(
                        "/discuss/delete",
                        "/data/**"
                )
                .hasAnyAuthority(
                        AUTHORITY_ADMIN
                )
                //除了这些以外所有路径都可以
                .anyRequest().permitAll()
                .and().csrf().disable();
        //权限不够的时候
        //authenticationEntryPoint()这个代表没登录时权限不足出现的问题
        //accessdeniedHandler代表登录后权限不足的问题
        http.exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    //没有登录时候的处理
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        //如果是异步请求返回json字符串
                          String xRequestedWith = request.getHeader("x-requested-with");
                          if ("XMLHttpRequest".equals(xRequestedWith)){
                              response.setContentType("application/plain;charset=utf-8");
                              PrintWriter writer = response.getWriter();
                              writer.write(CommunityUtil.getJSONString(403,"你还没有登录哦！"));
                          }else {
                              response.sendRedirect(request.getContextPath()+"/login");

                          }
                    }
                })
                .accessDeniedHandler(new AccessDeniedHandler() {
                    //权限不足时候的处理
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
                        //如果是异步请求返回json字符串
                        String xRequestedWith = request.getHeader("x-requested-with");
                        if ("XMLHttpRequest".equals(xRequestedWith)){
                            response.setContentType("application/plain;charset=utf-8");
                            PrintWriter writer = response.getWriter();
                            writer.write(CommunityUtil.getJSONString(403,"你没有访问该功能的权限哦！"));
                        }else {
                            response.sendRedirect(request.getContextPath()+"/denied");

                        }
                    }
                });
        //security自动管理logout退出，会在fiiter时候就会拦截到退出请求，导致无法直接我们自己的退出controller，我们要绕过这个自动的，要走我们自己的
        //覆盖默认的逻辑
        http.logout().logoutUrl("/sercuritylogout");//框架默认路径logout，现在给他改了，就走不了这个默认路径了，善意的欺骗

    }
}

/**
 * 现在我们没有走security的认证，所以拿不到UsernamePasswordAuthenticationToken，存储不进去，
 * 无法在SecurityContextHolder.getContext().getAuthentication().getPrincipal()拿到认证信息;，
 * 那么无法进行授权
 */
