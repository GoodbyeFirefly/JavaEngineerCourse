package com.xxy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxy.entity.Bill;
import com.xxy.mapper.BillMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BillService {
    @Resource
    private BillMapper billMapper;

    public List<Bill> list(Bill b) {
        return billMapper.select(b);
    }

    public int insert(Bill b) {
        return billMapper.insert(b);
    }

    public Bill selectByPrimaryKey(Long id) {
        return billMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(Bill b) {
        return billMapper.updateByPrimaryKey(b);
    }

    public int delete(Long id) {
        return billMapper.deleteByPrimaryKey(id);
    }

    public PageInfo<Bill> listPage(Bill b, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            billMapper.select(b);
        });
    }
}
