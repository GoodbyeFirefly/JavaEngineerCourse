package com.xxy.service;

import com.xxy.mapper.UserMapper;
import com.xxy.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<User> findAll(boolean limit, int offset, int pageNumber) {
        if (limit) {
            return userMapper.findByLimit(offset, pageNumber);
        } else {
            return userMapper.findAll();
        }
    }

    public int getTotal() {
        return userMapper.getTotal();
    }

    public Boolean insert(User user) {
        return userMapper.insert(user);
    }

    public User findByPhone(String userphone) {
        return userMapper.findByPhone(userphone);
    }

    public Boolean update(User user) {
        return userMapper.update(user);
    }

    public Boolean delete(String number) {
        return userMapper.delete(number);
    }

    public Map<String, Integer> console() {
        return userMapper.console();
    }

}
