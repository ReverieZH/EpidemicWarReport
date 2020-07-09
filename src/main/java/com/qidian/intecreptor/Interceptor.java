package com.qidian.intecreptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("拦截器：");
        httpServletResponse.setContentType("text/html");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");

        HttpSession session=httpServletRequest.getSession();

        String path=httpServletRequest.getServletPath();

        String requestUri = httpServletRequest.getRequestURI();
        String contextPath = httpServletRequest.getContextPath();
        String url = requestUri.substring(contextPath.length());
        System.out.println("path:"+path+" uri:"+requestUri+" contextPath:"+contextPath+"  url:"+url);
        if(path.equals("/login.html")||path.equals("login.do")||path.equals("//login.html")){
            System.out.println("未拦截");
            return true;
        }else {
            Object username= session.getAttribute("username");
            System.out.println(username);
            if(username==null){
                System.out.println("拦截1");
                httpServletResponse.sendRedirect(contextPath+"/login.html");
                return false;
            } else {
                System.out.println("通行");
               return true;
            }
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("text/html; charset=utf-8");
    }
}
