package com.xxy.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface AdminMapper {
    /**
     * 根据用户名更新登陆时间和登录ip
     * @param username
     * @param loginTime
     * @param ip
     */
    void updateLoginTime(@Param("username") String username,
                         @Param("logintime") Date loginTime,
                         @Param("ip") String ip);

    /**
     * 判断登录是否成功
     * @param username
     * @param password
     * @return
     */
    String login(@Param("username") String username,
                  @Param("password") String password);
}
