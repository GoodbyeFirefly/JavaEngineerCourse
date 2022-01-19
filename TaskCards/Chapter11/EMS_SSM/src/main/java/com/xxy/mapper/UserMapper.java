package com.xxy.mapper;

import com.xxy.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> findAll();
    List<User> findByLimit(@Param("offset") int offset, @Param("pagenumber") int pageNumber);

    int getTotal();

    Boolean insert(User user);

    User findByPhone(String userphone);

    Boolean update(User user);

    Boolean delete(String number);

    Map<String, Integer> console();
}
