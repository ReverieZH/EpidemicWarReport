package com.qidian.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class WebFilter implements Filter {
    private String excludedPage;
    private String[] excludedPages;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedPage = filterConfig.getInitParameter("ignores");//此处的ignores就是在web.xml定义的名称一样。
        if (excludedPage != null && excludedPage.length() > 0){
            excludedPages = excludedPage.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置Servlet
        servletResponse.setContentType("text/html");
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");

        HttpServletRequest httpRequest= (HttpServletRequest) servletRequest;
        HttpSession session=httpRequest.getSession();

        String path=httpRequest.getServletPath();

        String requestUri = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String url = requestUri.substring(contextPath.length());
        System.out.println("path:"+path+" uri:"+requestUri+" contextPath:"+contextPath+"  url:"+url);

            boolean flag = false;
            for (String page:excludedPages) {
                if (path.equals(page)){
                    flag = true;
                    break;
                }
            }

            if(flag){
                filterChain.doFilter(servletRequest,servletResponse);
            }else if(path.endsWith("/login.html")||path.endsWith("login.do")) {
             filterChain.doFilter(servletRequest, servletResponse);
           }else {
            Object username= session.getAttribute("username");
            System.out.println(username);
            if(username==null){
                ((HttpServletResponse)servletResponse).sendRedirect(contextPath+"/login.html");
            } else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
