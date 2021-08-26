//package com.cms.filter;
//
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//@RequestMapping("/*")
//@Component
//public class LoginFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("------------------------------------------------------------------------------------过滤器初始化完成");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        //过滤器
//        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
//        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;
//        String token = httpServletRequest.getHeader("x-token");
//        //token获取完毕以后 进行操作判断
//        System.out.println("------------------------------------------------------------------------------------过滤器收到一个请求 ");
//        filterChain.doFilter(httpServletRequest,httpServletResponse);
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("-----------------------------------------------------------------------------------过滤器销毁");
//    }
//}
