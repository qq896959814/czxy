package com.czxy.bookstore.dao;

import com.czxy.bookstore.domain.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by 89695 on 2019/5/28.
 */
public interface OrderMapper extends Mapper<Order> {

    /**
     * 根据用户id查找订单
     * @param uid 用户id
     * @return 订单的集合
     */
    @Select("select oid,ordertime,total_price total,state,address,name,telephone,uid from orders where uid = #{uid}")
    List<Order> findOrderByUid(@Param("uid") String uid);
}
