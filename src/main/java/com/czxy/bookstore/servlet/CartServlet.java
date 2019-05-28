package com.czxy.bookstore.servlet;

import cn.itcast.servlet.BaseServlet;
import com.czxy.bookstore.domain.Cart;
import com.czxy.bookstore.domain.Product;
import com.czxy.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 刘正风 on 2019/5/27.
 * 购物车相关的servlet
 */
public class CartServlet extends BaseServlet {

    private ProductService productService = new ProductService();

    /**
     * 加入购物车
     * @return
     */
    public String addsToCart() throws IOException {

        //获取参数
        String pid = getRequest().getParameter("pid");
        Integer count = Integer.parseInt(getRequest().getParameter("quantity"));

        //调用ProductService，根据pid获取购物车项中的商品对象
        Product productById = productService.findProductById(pid);

        //处理结果
        //从session中获取购物车
        Cart cart = (Cart) getSession().getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            getSession().setAttribute("cart",cart);
        }

        //将商品和数量转成购物车项，放入到购物车中  ？？？？？用不用再覆盖cart？？？
        cart.addCart(productById,count);

        //返回页面
        return "redirect:/jsp/cart.jsp";
    }

    /**
     * 删除购物车项
     * @return
     */
    public String delCartItem() {
        //获取参数
        String delPid = getRequest().getParameter("pid");

        //处理数据
        Cart cart = (Cart) getSession().getAttribute("cart");
        cart.removeCart(delPid);
        //返回页面
        return "redirect:/jsp/cart.jsp";
    }

    /**
     * 清空购物车
     * @return
     */
    public String clearCartItem() {

        //处理数据
        Cart cart = (Cart) getSession().getAttribute("cart");
        cart.clearCart();
        //返回页面
        return "redirect:/jsp/cart.jsp";
    }
}
