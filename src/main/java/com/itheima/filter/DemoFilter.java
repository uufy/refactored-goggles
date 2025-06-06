package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")//  拦截所有请求
@Slf4j
public class DemoFilter implements Filter {

    // 初始化方法, 只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      log.info("初始化过滤器");
    }

    // 过滤方法, 拦截到请求都会执行
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到了请求。。。。放行前");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        log.info("拦截到了请求。。。。放行后");

    }

    // 销毁方法, 只执行一次
    @Override
    public void destroy() {
        log.info("销毁过滤器");
    }
}
