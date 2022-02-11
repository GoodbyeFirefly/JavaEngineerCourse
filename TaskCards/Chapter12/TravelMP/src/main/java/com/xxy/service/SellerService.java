package com.xxy.service;

import com.github.pagehelper.PageInfo;
import com.xxy.domain.Seller;

import java.util.List;

public interface SellerService {
    public PageInfo<Seller> findPage(Seller conditioin, int pageNum, int pageSize);
    public List<Seller> find(Seller conditioin);
    public int add(Seller seller);
    public Seller findById(Integer id);
    public int update(Seller seller);
    public int delete(Integer id);
}
