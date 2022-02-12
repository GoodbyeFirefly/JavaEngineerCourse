package com.xxy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxy.domain.Seller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerDao extends BaseMapper<Seller> {
    // RouteDao中要用到这里的方法（无法直接使用内置方法），所以需要添加
    @Select("SELECT * FROM tab_seller WHERE sid=#{id}")
    public Seller findById(Integer id);
}
