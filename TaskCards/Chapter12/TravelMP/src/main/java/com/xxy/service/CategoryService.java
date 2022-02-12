package com.xxy.service;

import com.xxy.domain.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> find();
    public int add(Category category);
    public Category findById(Integer id);
    public int update(Category category);
    public int delete(Integer id);
}
