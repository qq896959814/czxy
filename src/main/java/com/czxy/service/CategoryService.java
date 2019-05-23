package com.czxy.service;

import com.czxy.dao.CategoryMapper;
import com.czxy.domain.Category;
import com.czxy.utils.MybatisUtils;

import java.io.IOException;
import java.util.List;

public class CategoryService {

    public List<Category> findAll() throws IOException {
        CategoryMapper mapper = MybatisUtils.getMapper(CategoryMapper.class);
        List<Category> categories = mapper.selectAll();
        MybatisUtils.commitAndclose();
        return categories;
    }
}
