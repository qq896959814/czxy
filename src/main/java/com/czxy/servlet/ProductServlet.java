package com.czxy.servlet;

import cn.itcast.servlet.BaseServlet;
import com.czxy.domain.Product;
import com.czxy.service.ProductService;
import com.github.pagehelper.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductServlet extends BaseServlet {
    //定义service
    private ProductService productService = new ProductService();

    public String findProductById() throws IOException {
        String pid = getRequest().getParameter("pid");
        Product product = productService.findProById(pid);
        getRequest().setAttribute("product",product);
        return "forward:/jsp/product_info.jsp";
    }

    public String findProByCid() throws IOException {
        String cid = getRequest().getParameter("cid");
        //默认的分页页码为1
        int pageNum = 1;
        //获取页面传入的请求页码
        String pageNumber = getRequest().getParameter("pageNumber");
        if (pageNumber != null && !"".equals(pageNumber)){
            pageNum = Integer.parseInt(pageNumber);
        }
        int pageSize = 12;
        PageInfo<Product> pageInfo = productService.findPageByCid(cid,pageNum,pageSize);
        return "forward:/jsp/product_list.jsp";
    }
}
