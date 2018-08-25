package com.demu.codeFilter;

//
//  可以在filter中根据条件决定是否调用chain.doFilter(request, response)方法，
//  即是否让目标资源执行 在让目标资源执行之前，可以对request\response作预处理，再让目标资源执行

import javax.servlet.*;
import java.io.IOException;

public class MyCodeFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤开始");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //对request和response进行一些预处理
               request.setCharacterEncoding("UTF-8");
               response.setCharacterEncoding("UTF-8");
               response.setContentType("text/html;charset=UTF-8");


               chain.doFilter(request, response);  //让目标资源执行，放行

    }

    @Override
    public void destroy() {
        System.out.println("过滤结束");

    }
}
