package com.demu.servlet;

import com.demu.dao.UserDAO;
import com.demu.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deluser")
public class UserDelServlet extends HttpServlet {
    private UserDAO userDAO=new UserDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String id=req.getParameter("id");
        String type=req.getParameter("type");

        //执行删除或者锁定
        if ("lock".equals(type)){
            //执行锁定操作，update操作
            Users users=new Users();
            users.setId(Integer.parseInt(id));
            users.setUserStatus(1);

            userDAO.updateUsers(users);

        }else if ("unlock".equals(type)){
            //执行解锁操作，update操作
            Users users=new Users();
            users.setId(Integer.parseInt(id));
            users.setUserStatus(0);

            userDAO.updateUsers(users);

        }else if ("del".equals(type)){
            //执行删除操作 ，delete操作
            userDAO.delUsers(Integer.parseInt(id));

        }

        //重定向到首页
        resp.sendRedirect("/index");

    }




}

