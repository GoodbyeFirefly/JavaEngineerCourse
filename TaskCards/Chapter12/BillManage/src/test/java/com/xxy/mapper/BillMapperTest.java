package com.xxy.mapper;

import com.xxy.entity.Bill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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

}