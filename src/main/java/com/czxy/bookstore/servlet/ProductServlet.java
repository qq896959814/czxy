package com.czxy.bookstore.servlet;

import cn.itcast.servlet.BaseServlet;
import com.czxy.bookstore.domain.Category;
import com.czxy.bookstore.domain.Product;
import com.czxy.bookstore.service.CategoryService;
import com.czxy.bookstore.service.ProductService;
import com.github.pagehelper.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 刘正风 on 2019/5/27.
 * 商品相关的servlet
 */
public class ProductServlet extends BaseServlet {

    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();

    /**
     * 展示商品详情
     * @return
     */
    public String findProductById() throws IOException {

        //接收用户参数
        String pid = getRequest().getParameter("pid");
        //调用service,根据商品id获取商品对象，根据分类id获取分类对象
        Product product = productService.findProductById(pid);
        Category category = categoryService.findCategoryById(product.getCid());
        //处理结果,将产品和产品的分类放到reqeust中
        getRequest().setAttribute("product",product);
        getRequest().setAttribute("category",category);

        //请求转发页面
        return "jsp/product_info.jsp";
    }

    /**
     * 根据分类分页查询该分类下的商品
     * @return
     */
    public String findProByCid() throws IOException {

        //接收数据,接收分类id,接收当前第几页
        String cid = getRequest().getParameter("cid");
        Integer pageNumber = Integer.parseInt(getRequest().getParameter("pageNumber"));
        //调用service方法，
        PageInfo<Product> pageInfo = productService.findProductByCid(cid,pageNumber,12);
        //处理数据，
        getRequest().setAttribute("pageInfo",pageInfo);
        //返回页面
        return "jsp/product_list.jsp";
    }
}
