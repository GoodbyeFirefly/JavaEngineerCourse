package com.xxy.service;

import com.xxy.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;

    public boolean login(String username, String password) {
        String login = adminMapper.login(username, password);
        return login != null;
    }
    public void updateLoginTime(String username, Date loginTime, String ip) {
        adminMapper.updateLoginTime(username, loginTime, ip);
    }
}
