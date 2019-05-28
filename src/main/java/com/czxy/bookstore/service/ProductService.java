package com.czxy.bookstore.service;

import com.czxy.bookstore.dao.ProductMapper;
import com.czxy.bookstore.domain.Product;
import com.czxy.bookstore.utils.MybatisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.IOException;
import java.util.List;



public class ProductService {

    /**
     * 获取所有热门商品集合
     * @return
     */
    public List<Product> getHotProducts() throws IOException {

        //获取mapper
        ProductMapper mapper = MybatisUtils.getMapper(ProductMapper.class);
        //读取所有的热门商品
        List<Product> hotProducts = mapper.findHotProducts();
        //关闭资源
        MybatisUtils.commitAndclose();
        //返回数据
        return hotProducts;
    }

    /**
     * 通过分页的方式，获取热门商品
     * @param pageNum 当前第几页
     * @param pageSize 每页多少条数据
     * @return
     */
    public PageInfo<Product> findHotProPage(int pageNum, int pageSize) throws IOException {

        //获取mapper
        ProductMapper mapper = MybatisUtils.getMapper(ProductMapper.class);

        //开启分页
        PageHelper.startPage(pageNum,pageSize);

        //读取所有的热门商品
        List<Product> hotProducts = mapper.findHotProducts();

        //将该页的数据，封装成PageInfo对象
        PageInfo<Product> pageInfo = new PageInfo<Product>(hotProducts);

        //关闭资源
        MybatisUtils.commitAndclose();
        //返回数据
        return pageInfo;
    }

    /**
     * 根据商品id获取商品对象
     * @param pid
     * @return
     */
    public Product findProductById(String pid) throws IOException {

        //获取mapper
        ProductMapper mapper = MybatisUtils.getMapper(ProductMapper.class);
        //调用mapper方法完成逻辑
        Product product = mapper.selectByPrimaryKey(pid);
        //关闭资源
        MybatisUtils.commitAndclose();
        //返回结果
        return product;
    }

    /**
     * 根据分类分页查询该分类下的商品
     * @param cid 分类id
     * @param pageNum 第几页
     * @param pageSize 每页多少条数据
     * @return
     */
    public PageInfo<Product> findProductByCid(String cid, int pageNum, int pageSize) throws IOException {

        //获取mapper
        ProductMapper mapper = MybatisUtils.getMapper(ProductMapper.class);

        //开启分页
        PageHelper.startPage(pageNum,pageSize);

        //读取该分类的该页的商品
        List<Product> products = mapper.findProductByCid(cid);

        //将该页的数据，封装成PageInfo对象
        PageInfo<Product> pageInfo = new PageInfo<Product>(products);

        //关闭资源
        MybatisUtils.commitAndclose();
        //返回数据
        return pageInfo;
    }
}
