package com.qidian.intecreptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StudentInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession session=httpServletRequest.getSession();

        String path=httpServletRequest.getServletPath();

        String requestUri = httpServletRequest.getRequestURI();
        String contextPath = httpServletRequest.getContextPath();
        String url = requestUri.substring(contextPath.length());
        System.out.println("path:"+path+" uri:"+requestUri+" contextPath:"+contextPath+"  url:"+url);
        if(path.equals("/studentlogin.html")||path.equals("studentlogin.do")||path.equals("//studentlogin.html")){
            return true;
        }else {
            if(session.getAttribute("sno")==null){
                httpServletResponse.sendRedirect(contextPath+"/studentlogin.html");
                return false;
            } else {
                return true;
            }
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
