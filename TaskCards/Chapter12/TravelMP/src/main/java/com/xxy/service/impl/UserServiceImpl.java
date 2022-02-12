package com.xxy.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxy.dao.UserDao;
import com.xxy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements com.xxy.service.UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo<User> findPage(User conditioin, int pageNum, int pageSize) {
        // 查询条件（input组件的value）为空时，默认的为''而不是null
        // mp内置的条件查询会将''作为条件之一，从而导致查询不出结果
        if (conditioin != null) {
            if ("".equals(conditioin.getName())) conditioin.setName(null);
            if ("".equals(conditioin.getEmail())) conditioin.setEmail(null);
        }
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            // 需要将查询条件作为参数传入
            userDao.selectList(Wrappers.<User>query(conditioin));
        });
    }

    @Override
    public List<User> find(User conditioin) {
        return userDao.selectList(Wrappers.<User>query());
    }

    @Override
    public int add(User user) {
        return userDao.insert(user);
    }

    @Override
    public User findById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public int update(User user) {
        return userDao.updateById(user);
    }

    @Override
    public int delete(Integer id) {
        return userDao.deleteById(id);
    }
}
