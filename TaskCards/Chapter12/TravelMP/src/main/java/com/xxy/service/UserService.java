package com.xxy.service;

import com.github.pagehelper.PageInfo;
import com.xxy.domain.User;

import java.util.List;

public interface UserService {
    public PageInfo<User> findPage(User conditioin, int pageNum, int pageSize);
    public List<User> find(User conditioin);
    public int add(User user);
    public User findById(Integer id);
    public int update(User user);
    public int delete(Integer id);
}
