package com.xxy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxy.domain.User;
import org.springframework.stereotype.Repository;

// 直接使用内置方法，没有映射文件
@Repository
public interface UserDao extends BaseMapper<User> {
}
