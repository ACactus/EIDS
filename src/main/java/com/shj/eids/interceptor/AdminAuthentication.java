package com.shj.eids.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: AdminAuthentication
 * @Description:
 * @Author: ShangJin
 * @Create: 2020-03-22 18:54
 **/

public class AdminAuthentication implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.debug("管理员身份验证....");
        boolean isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
        if(isAdmin){
            logger.debug("管理员，放行");
            return true;
        }else{
            logger.debug("非管理员，重定向至主页");
            request.setAttribute("msg", "权限不足");
            request.getRequestDispatcher("/index").forward(request, response);
            return false;
        }
    }
}