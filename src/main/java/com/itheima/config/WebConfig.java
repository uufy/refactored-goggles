package com.itheima.config;

import com.itheima.interceptor.DemoInterceptor;
import com.itheima.interceptor.TokenInterceper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 配置类
*
* */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private  DemoInterceptor demoInterceptor;
//
//    @Autowired
//    private TokenInterceper tokenInterceper;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(demoInterceptor).addPathPatterns("/**");
//
//        registry.addInterceptor(tokenInterceper)
//                .addPathPatterns("/**")// 拦截所有请求
//                .excludePathPatterns("/login");//不拦截哪些请求
//
//
//    }
}
