package com.czxy.bookstore.service;

import com.czxy.bookstore.dao.OrderItemMapper;
import com.czxy.bookstore.dao.OrderMapper;
import com.czxy.bookstore.dao.ProductMapper;
import com.czxy.bookstore.domain.Order;
import com.czxy.bookstore.domain.OrderItem;
import com.czxy.bookstore.domain.Product;
import com.czxy.bookstore.domain.User;
import com.czxy.bookstore.utils.MybatisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.IOException;
import java.util.List;

/**
 * Created by 89695 on 2019/5/28.
 */
public class OrderService {
    public void save(Order order) throws IOException {
        //1存储订单
        OrderMapper om = MybatisUtils.getMapper(OrderMapper.class);
        om.insert(order);
        //2存储订单项
        //2.1获取订单中所有的订单项
        List<OrderItem> orderItemList = order.getOrderItemList();
        //2.2获取每个订单项
        for (OrderItem orderItem : orderItemList) {
            //2.3存储每个订单
            OrderItemMapper oim = MybatisUtils.getMapper(OrderItemMapper.class);
            orderItem.setOid(orderItem.getOid());
            oim.insert(orderItem);
        }

        MybatisUtils.commitAndclose();

    }


    /**
     * 根据用户查询所有订单
     * @param loginUser 要查询的用户
     * @return 该用户下的所有订单
     */
    public List<Order> findOrderByUid(User loginUser) throws IOException {

        //1获取查询条件：用户id
        String uid = loginUser.getUid();
        //2进行查询动作，查询订单表
        //2.1获取订单Mapper
        OrderMapper om = MybatisUtils.getMapper(OrderMapper.class);
        //2.2根据用户id查询订单表
        List<Order> orders = om.findOrderByUid(uid);
        //3为每个Order的orderItemList(该订单的所有订单项)字段赋值
        //3.1获取OrderItemMapper
        OrderItemMapper oim = MybatisUtils.getMapper(OrderItemMapper.class);
        //4.1获取ProductMapper
        ProductMapper pm = MybatisUtils.getMapper(ProductMapper.class);
        //3.2迭代订单集合，依次获取每一个订单
        for (Order order : orders) {
            //3.3获取查询orderItem的条件：订单id
            String oid = order.getOid();
            //3.4调用mapper方法
            List<OrderItem> orderItems = oim.findItemsByOid(oid);
            //4为每个OrderItem的product(该订单项的商品)字段赋值
            //4.2迭代订单项集合，依次获取每一个订单项
            for (OrderItem orderItem : orderItems) {
                //4.3获取查询商品的条件：商品pid
                String pid = orderItem.getPid();
                //4.4调用mapper方法
                Product product = pm.selectByPrimaryKey(pid);
                //4.5为订单项的商品字段赋值
                orderItem.setProduct(product);
            }
            //3.5将订单项集合放入订单
            order.setOrderItemList(orderItems);
        }
        //5返回该用户下的所有订单
        return orders;
    }

    /**
     * 分页查询订单方法
     * @param pageNumber
     * @param pageSize
     * @param loginUser
     * @return
     */
    public PageInfo<Order> findOrderPageByUid(int pageNumber, int pageSize, User loginUser) throws IOException {

        //1.1获取查询条件：用户id
        String uid = loginUser.getUid();
        //2进行查询动作，查询订单表
        //2.1获取订单Mapper
        OrderMapper om = MybatisUtils.getMapper(OrderMapper.class);
        //======分页=============================================
        PageHelper.startPage(pageNumber,pageSize);
        //======================================================
        //2.2根据用户id查询订单表
        List<Order> orders = om.findOrderByUid(uid);
        //3为每个Order的orderItemList(该订单的所有订单项)字段赋值
        //3.1获取OrderItemMapper
        OrderItemMapper oim = MybatisUtils.getMapper(OrderItemMapper.class);
        //4.1获取ProductMapper
        ProductMapper pm = MybatisUtils.getMapper(ProductMapper.class);
        //3.2迭代订单集合，依次获取每一个订单
        for (Order order : orders) {
            //3.3获取查询orderItem的条件：订单id
            String oid = order.getOid();
            //3.4调用mapper方法
            List<OrderItem> orderItems = oim.findItemsByOid(oid);
            //4为每个OrderItem的product(该订单项的商品)字段赋值
            //4.2迭代订单项集合，依次获取每一个订单项
            for (OrderItem orderItem : orderItems) {
                //4.3获取查询商品的条件：商品pid
                String pid = orderItem.getPid();
                //4.4调用mapper方法
                Product product = pm.selectByPrimaryKey(pid);
                //4.5为订单项的商品字段赋值
                orderItem.setProduct(product);
            }
            //3.5将订单项集合放入订单
            order.setOrderItemList(orderItems);
        }
        //5将该用户下的所有订单封装成pageinfo页面信息对象
        //=======================================================
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        return pageInfo;
    }
}
