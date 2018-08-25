package com.demu.dao;

import com.demu.entity.Users;
import com.demu.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDAO {
    private SqlSession sqlSession;
    private List<Users> list;
    private Users user;

    /**
     * 防止不同操作，关闭一个sqlSession
     * @return
     */
    private SqlSession getSession() {
        sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        return sqlSession;
    }

    /**
     * 查询全部用户
     * @return
     */
    public List<Users> findAll() {
        try {
            list = getSession().selectList("findUsers");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return list;
    }

    /**
     * 根据id查询用户
     * @return单个用户
     */
    public Users findById(int id){
        try {
            user=getSession().selectOne("findUsers",new Users(id));

        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }

        return user;
    }

}
