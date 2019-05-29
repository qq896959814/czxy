package com.czxy.bookstore.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import com.czxy.bookstore.domain.*;
import com.czxy.bookstore.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

/**
 * Created by 89695 on 2019/5/28.
 */
public class OrderServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    private OrderService orderService = new OrderService();
    /*
     * 将购物车转为订单，生成订单
     */
    public String saveOrder() throws IOException {

        //1获取数据
        //1.1获取session中的购物车信息
        Cart cart = (Cart) this.getSession().getAttribute("cart");
        //1.2获取session中的用户信息
        User loginUser = (User) this.getSession().getAttribute("loginUser");
        //如果用户没有登陆，则在生成订单时，跳转到登陆
        if(loginUser==null) {
            return "redirect:/jsp/login.jsp";
        }

        //2处理数据
        //2.1将购物车cart转为订单order
        Order order = new Order();
        //2.1.1设置oid
        //order.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
        String oid = CommonUtils.uuid();
        order.setOid(oid);
        //也可以不自己写主键，让mybatis自动生成主键——————这次的业务逻辑不能使用:因为后边商品项需要用到，所以手动生成
        //2.1.2设置下单时间
        order.setOrdertime(new Date());
        //2.1.3设置总金额
        order.setTotal(cart.getTotal());
        //2.1.4设置状态未初始状态未1
        order.setState(1);
        //2.1.5address,name,telephone会在后边付款前的逻辑中由页面用户传入
        //2.1.6设置用户
        order.setUid(loginUser.getUid());
        order.setUser(loginUser);

        //3.将购物车项cartItem转为订单项orderItem
        //3.1准备集合，用于存放所有转成的订单项
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        //3.1将所有购物车项从购物车取出
        Map<String, CartItem> cartItems = cart.getCartItems();
        //3.2遍历所有购物车项的map集合
        Set<String> cartItemsKeys = cartItems.keySet();
        //3.2.1使用增强for循环将每个key取出
        for (String thisItemKey : cartItemsKeys) {
            //3.2.2通过键获取值(CartItem)
            CartItem thisCartItem = cartItems.get(thisItemKey);
            //3.3从购物车项中取出数据，放置到订单项中
            //3.3.1创建订单项
            OrderItem orderItem = new OrderItem();
            //3.3.2取出与输入
            //3.3.3itemsid直接mybatis生成
            //3.3.4设置订单项的购买数量
            orderItem.setCount(thisCartItem.getCount());
            //3.3.5设置小计
            orderItem.setSubtotal(thisCartItem.getSubTotal());
            //3.3.6设置订单id，订单id在之前已经生成了并记录了！↑
            orderItem.setOid(oid);
            orderItem.setOrder(order);
            //3.3.6设置商品id
            orderItem.setPid(thisCartItem.getProduct().getPid());
            orderItem.setProduct(thisCartItem.getProduct());
            //将新生成的购物车项放到集合中
            orderItemList.add(orderItem);
        }

        //2.1.7设置订单当中的订单项集合
        order.setOrderItemList(orderItemList);

        //4.将订单与订单项存储到数据库中
        //调用service的功能即可
        orderService.save(order);

        //5.清空购物车
        cart.clearCart();

        //6.返回数据
        //向request中，添加attr，key：“order”，value：order对象
        this.getRequest().setAttribute("order", order);
        return "forward:/jsp/order_info.jsp";
    }

    /**
     * 根据用户查找订单
     */
    public String findOrderByUid() throws IOException {

        //获取数据
        User loginUser = (User) this.getSession().getAttribute("loginUser");
        //滤空判断
        if(loginUser == null) {
            return "redirect:/jsp/login.jsp";
        }
        //处理数据
        List<Order> list = orderService.findOrderByUid(loginUser);
        this.getRequest().setAttribute("list", list);
        //返回数据
        return "forward:/jsp/order_list.jsp";
    }

    /**
     * 根据用户查找分页订单
     */
    public String findOrderPageByUid() throws IOException {

        //获取数据
        User loginUser = (User) this.getSession().getAttribute("loginUser");

        //获取页数和页面尺寸
        int pageNumber = 1;
        int pageSize = 1;

        //获取页面请求的页数
        String sPageNumber = this.getRequest().getParameter("pageNumber");

        if(sPageNumber!=null && !"".equals(sPageNumber)) {
            pageNumber = Integer.parseInt(sPageNumber);
        }

        //滤空判断
        if(loginUser == null) {
            return "redirect:/jsp/login.jsp";
        }
        //处理数据
        PageInfo<Order> pageInfo = orderService.findOrderPageByUid(pageNumber,pageSize,loginUser);
        this.getRequest().setAttribute("pageInfo", pageInfo);
        //返回数据
        return "forward:/jsp/order_list2.jsp";
    }

    /**
     * 注意UUID自己书写时，需要剔除中间的分隔符
     */
    @Test
    public void test0() {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(id);

        System.out.println(CommonUtils.uuid());
    }
}
