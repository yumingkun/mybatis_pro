package com.demu.listener;



import com.demu.utils.SqlSessionFactoryUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener//（注解方式）启动监听 以使得该监听器可以起作用。

//ServletContextListener 接口，它能够监听 ServletContext 对象的生命周期，实际上就是监听 Web 应用的生命周期。
//ServletContext 对象是一个为整个 web 应用提供共享的内存，任何请求都可以访问里面的内容

public class InitSqlSessionListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("容器加载中...");
        // 初始化我们的SqlSesionFactory对象
        SqlSessionFactoryUtils.initSqlSessionFactory();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("容器销毁中...");
        // 关闭SqlSession对象
        SqlSessionFactoryUtils.close();
    }
}
