package com.atguigu.springbootwebsimulateing.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //做一个登录验证，防止跳过登录，直接访问dashboard页面
        //这边检查数据，并设置放行规则
        Object username = request.getSession().getAttribute("username");
        if (username==null){
            //未登录
            request.setAttribute("msg", "没有权限请登录");
//            转发使用的是getRequestDispatcher()方法;重定向使用的是sendRedirect();
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }else{

        return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
