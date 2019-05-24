package com.czxy.service;

import com.czxy.dao.ProductMapper;
import com.czxy.domain.Product;
import com.czxy.utils.MybatisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.List;

public class ProductService {

    public PageInfo<Product> findHotProPage(int pageNum, int size) throws IOException {
        //设置分页条件
        PageHelper.startPage(pageNum,size);
        System.out.println(size);
        //获取mapper
        ProductMapper mapper = MybatisUtils.getMapper(ProductMapper.class);
        //创建条件
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        //加入热门字段与是否下架字段判断
        criteria.andEqualTo("is_hot",1).andEqualTo("pflag",0);
        example.setOrderByClause("pdate Desc");
        List<Product> list = mapper.selectByExample(example);
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);
        MybatisUtils.commitAndclose();
        return pageInfo;

    }

    public Product findProById(String pid) throws IOException {
        ProductMapper mapper = MybatisUtils.getMapper(ProductMapper.class);
        Product product = mapper.selectByPrimaryKey(pid);
        MybatisUtils.commitAndclose();
        return product;
    }

    public PageInfo<Product> findPageByCid(String cid, int pageNum, int pageSize) throws IOException {
        PageHelper.startPage(pageNum,pageSize);
        ProductMapper mapper = MybatisUtils.getMapper(ProductMapper.class);
        List<Product> pros = mapper.findProByCid(cid);
        PageInfo<Product> pageInfo = new PageInfo<>(pros);
        MybatisUtils.commitAndclose();
        return pageInfo;
    }
}
