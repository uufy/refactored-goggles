package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.http.HttpResponse;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取请求路径，
        String requestURI = request.getRequestURI();
        //并判断是否是登录请求
        if(requestURI.contains("/login")){
            log.info("登录请求,放行：{}",requestURI);
            filterChain.doFilter(request,response);
            return;
        }

        //获取登录token
        String token = request.getHeader("token");

        //判断token是否存在，如果不存在，说明用户未登录，返回错误信息401
        if(token == null || token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(401);
            return;
        }

        //校验令牌是否正确，错误返回错误信息401
        try {
            JwtUtils.parseJwt(token);
        }catch (Exception e){
            log.info("令牌错误，响应401");
            response.setStatus(401);
            return;
        }


        //通过就放行
        log.info("令牌合格，放行");
        filterChain.doFilter(request,response);

    }


}
