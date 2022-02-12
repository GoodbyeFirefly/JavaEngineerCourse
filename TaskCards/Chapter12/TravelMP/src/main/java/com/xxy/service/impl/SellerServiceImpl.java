package com.xxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxy.dao.SellerDao;
import com.xxy.domain.Seller;
import com.xxy.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerDao sellerDao;
    @Override
    public PageInfo<Seller> findPage(Seller conditioin, int pageNum, int pageSize) {
        // 查询条件（input组件的value）为空时，默认的为''而不是null
        // mp内置的条件查询会将''作为条件之一，从而导致查询不出结果
        if (conditioin != null) {
            if ("".equals(conditioin.getSname())) conditioin.setSname(null);
            if ("".equals(conditioin.getAddress())) conditioin.setAddress(null);
        }
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            // 需要将查询条件作为参数传入
            sellerDao.selectList(new QueryWrapper<Seller>(conditioin));
        });
    }

    @Override
    public List<Seller> find(Seller conditioin) {
        return sellerDao.selectList(Wrappers.query());
    }

    @Override
    public int add(Seller seller) {
        return sellerDao.insert(seller);
    }

    @Override
    public Seller findById(Integer id) {
        return sellerDao.selectById(id);
    }

    @Override
    public int update(Seller seller) {
        return sellerDao.updateById(seller);
    }

    @Override
    public int delete(Integer id) {
        return sellerDao.deleteById(id);
    }
}
