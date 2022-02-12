package com.xxy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxy.domain.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends BaseMapper<Category> {
    // RouteDao中要用到这里的方法（无法直接使用内置方法），所以需要添加
    @Select("SELECT cname,cid FROM tab_category WHERE cid=#{id}")
    public Category findById(Integer id);
}
