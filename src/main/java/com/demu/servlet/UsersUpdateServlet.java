package com.demu.servlet;

import com.demu.dao.UserDAO;
import com.demu.entity.Users;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateusers")
public class UsersUpdateServlet extends HttpServlet {

    private UserDAO userDAO=new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取用户要更新的数据
        String id = req.getParameter("id");
        String nickname = req.getParameter("nickname");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String remark = req.getParameter("remark");

        // 创建用户对象
        Users user = new Users(Integer.parseInt(id), nickname, Integer.parseInt(age), gender, email, phone, new Date(),remark);

        // 提交更新
        userDAO.updateUsers(user);

        // 查看更新后的用户数据
        resp.sendRedirect("/detail?id=" + user.getId());

    }
}
