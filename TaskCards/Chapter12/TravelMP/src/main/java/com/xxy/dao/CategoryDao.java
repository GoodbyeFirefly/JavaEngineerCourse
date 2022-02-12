package com.xxy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxy.domain.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends BaseMapper<Category> {
}
