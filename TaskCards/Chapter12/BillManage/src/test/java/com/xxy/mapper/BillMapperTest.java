package com.xxy.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxy.entity.Bill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillMapperTest {
    @Resource
    private BillMapper billMapper;

    @Test
    public void testSelect() {
        billMapper.select(new Bill()).forEach(bill -> {
            System.out.println(bill);
        });
    }

    @Test
    public void testPageIncepter() {
        PageInfo<Bill> page = PageHelper.startPage(2, 2).doSelectPageInfo(() -> {
            billMapper.select(new Bill());
        });
        page.getList().forEach(bill -> {
            System.out.println(bill.getId() + "" + bill.getTitle());
        });
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