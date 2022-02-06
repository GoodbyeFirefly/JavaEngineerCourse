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
public class UserService implements com.xxy.service.UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo<User> findPage(User conditioin, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            userDao.selectList(Wrappers.<User>query());
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
