package com.xxy.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MybatisUtil {
    private static ThreadLocal<SqlSession> sqlSessionThreadLocal = new ThreadLocal<>();
    private static SqlSessionFactory factory;

    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSession getSqlSession() {
        SqlSession sqlSession = sqlSessionThreadLocal.get();
        if (sqlSession == null) {
            sqlSession = factory.openSession();
            sqlSessionThreadLocal.set(sqlSession);// 将sqlSession与线程绑定
        }
        return sqlSession;
    }

    public static void closeSqlSession() {
        SqlSession sqlSession = sqlSessionThreadLocal.get();
        if (sqlSession != null) {
            sqlSession.close();
            sqlSessionThreadLocal.remove();
        }
    }
}
