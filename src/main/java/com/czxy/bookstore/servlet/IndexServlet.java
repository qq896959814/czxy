package com.czxy.bookstore.servlet;

import cn.itcast.servlet.BaseServlet;
import com.czxy.bookstore.domain.Category;
import com.czxy.bookstore.domain.Product;
import com.czxy.bookstore.service.CategoryService;
import com.czxy.bookstore.service.ProductService;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.List;

/**
 * Created by 刘正风 on 2019/5/23.
 * 用于读取首页分类信息的servlet
 */
public class IndexServlet extends BaseServlet {

    //创建分类serivce，为servlet提供分类操作
    private CategoryService categoryService = new CategoryService();
    //创建商品service，为serlvet提供商品操作
    private ProductService productService = new ProductService();

//    /**
//     * 获取所有分类的信息，并请求转发到jsp/index.jsp
//     * @return
//     */
//    public String getCategory() throws IOException {
//
//        //获取所有分类
//        List<Category> categories =  service.findAll();
//
//        //将所有分类信息放到request中
//        getRequest().setAttribute("categories",categories);
//
//        //请求转发到jsp/index.jsp
//        return "forward:jsp/index.jsp";
//    }
    /**
     * 获取所有分类的信息，并返回所有分类信息
     * @return
     */
    public void getCategory() throws IOException {

        //获取所有分类
        List<Category> categories =  categoryService.findAll();

        //将所有分类的数据，转成JSON对象
        JSONArray jsonArray = JSONArray.fromObject(categories);

        //返回分类的JSON数据
        getResponse().getWriter().write(jsonArray.toString());
    }

    /**
     * 获取所有的热门商品以及最新商品(自己完成,最新商品查询的sql已经为大家写好了。照热门商品完成最新商品展示即可)
     * @return
     */
    public String showIndex() throws IOException {

        //获取所有的热门商品
        List<Product> hotProducts = productService.getHotProducts();

        //将所有热门商品放到request中
        getRequest().setAttribute("hotProducts",hotProducts);

        //请求转发到jsp/index.jsp
        return "forward:jsp/index.jsp";
    }

    /**
     *  使用分页方式，获取所有的热门商品以及最新商品(自己完成)
     * @return
     */
    public String showIndex2() throws IOException {
        //获取所有的热门商品(第一页信息，每页9条数据)
        PageInfo<Product> pageInfo = productService.findHotProPage(1,9);
        //将所有热门商品放到request中
        getRequest().setAttribute("pageInfo",pageInfo);
        //请求转发到jsp/index.jsp
        return "forward:/jsp/index.jsp";
    }
}
