package com.xxy.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xxy.dao.CategoryDao;
import com.xxy.domain.Category;
import com.xxy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> find() {
        return categoryDao.selectList(Wrappers.query());
    }

    @Override
    public int add(Category category) {
        return categoryDao.insert(category);
    }

    @Override
    public Category findById(Integer id) {
        return categoryDao.selectById(id);
    }

    @Override
    public int update(Category category) {
        return categoryDao.updateById(category);
    }

    @Override
    public int delete(Integer id) {
        return categoryDao.deleteById(id);
    }
}
