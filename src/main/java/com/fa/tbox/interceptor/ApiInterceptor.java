package com.fa.tbox.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("拦截到请求: {}", request.getRequestURL());

        return true;
    }
}
