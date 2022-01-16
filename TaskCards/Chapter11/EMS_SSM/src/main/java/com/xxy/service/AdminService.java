package com.xxy.service;

import com.xxy.mapper.AdminMapper;
import com.xxy.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminService {
    public static boolean login(String username, String password) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

        String login = adminMapper.login(username, password);

//        sqlSession.close();
        return login != null;
    }
    public static void updateLoginTime(String username, Date loginTime, String ip) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

        adminMapper.updateLoginTime(username, loginTime, ip);
        sqlSession.commit();// 增删改需要手动提交

//        sqlSession.close();
    }
}
