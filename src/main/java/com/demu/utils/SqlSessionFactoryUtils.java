package com.demu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;

//1：获取配置文件输入流
//2：创建会话工厂
//3：用会话工厂打开会话'

//sqlSession的作用：
//1：向sql语句传入参数
//2：执行sql语句
//3：获取执行sql语句的结果
//4：事务的控制

//如何获取sqlSession
//1;通过配置文件获取数据库连接的信息（输入流）
//2：通过配置信息构建SqlSessionFactory
//3:通过SqlSessionFactory打开数据库会话


public class SqlSessionFactoryUtils {
    private static String RESOURCE="mybatis-config.xml";

    private  static SqlSessionFactory sqlSessionFactory;

//    ThreadLocal的作用是提供线程内的局部变量，这种变量在多线程环境下访问时能够保证各个线程里变量的独立性
    private  static ThreadLocal<SqlSession> threadLocal=new ThreadLocal<SqlSession>();


    /**
     * 工厂对象初始化
     */
    public static void  initSqlSessionFactory(){
        try {
            InputStream is=Resources.getResourceAsStream(RESOURCE);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取工厂对象
     * @return
     */
    public static  SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }

    /**
     * 关闭sqlsession的方法
     */
    public  static  void close(){
        SqlSession session=threadLocal.get();
        if (session!=null){
            session.close();
            threadLocal.set(null);
        }

    }
}
