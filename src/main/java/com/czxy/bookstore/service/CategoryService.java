package com.czxy.bookstore.service;

import com.czxy.bookstore.dao.CategoryMapper;
import com.czxy.bookstore.domain.Category;
import com.czxy.bookstore.utils.MybatisUtils;

import java.io.IOException;
import java.util.List;


public class CategoryService {

    public List<Category> findAll() throws IOException {

        //获取分类Mapper
        CategoryMapper mapper = MybatisUtils.getMapper(CategoryMapper.class);

        //获取所有的分类
        List<Category> categories = mapper.selectAll();

        MybatisUtils.commitAndclose();

        return categories;
    }

    /**
     * 根据分类id获取分类
     * @param cid
     * @return
     */
    public Category findCategoryById(String cid) throws IOException {

        CategoryMapper mapper = MybatisUtils.getMapper(CategoryMapper.class);

        Category category = mapper.selectByPrimaryKey(cid);

        MybatisUtils.commitAndclose();

        return category;
    }
}
