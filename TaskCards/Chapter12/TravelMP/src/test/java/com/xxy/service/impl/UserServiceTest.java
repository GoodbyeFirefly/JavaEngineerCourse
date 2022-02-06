package com.xxy.service.impl;

import com.github.pagehelper.PageInfo;
import com.xxy.domain.User;
import com.xxy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void findPage() {
        User u = new User();
        PageInfo<User> page = userService.findPage(u, 1, 10);
        page.getList().forEach(System.out :: println);
        System.out.println("总行数=" + page.getTotal());
        System.out.println("当前页=" + page.getPageNum());
        System.out.println("每页行数=" + page.getPageSize());
        System.out.println("总页数=" + page.getPages());
        System.out.println("起始行数=" + page.getStartRow());
        System.out.println("是第一页=" + page.isIsFirstPage());
        System.out.println("是最后页=" + page.isIsLastPage());
        System.out.println("还有下一页=" + page.isHasNextPage());
        System.out.println("还有上一页=" + page.isHasPreviousPage());
        System.out.println("页码列表" + Arrays.toString(page.getNavigatepageNums()));
    }
}