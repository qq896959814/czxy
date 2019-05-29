package com.czxy.bookstore.dao;

import com.czxy.bookstore.domain.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderItemMapper extends Mapper<OrderItem> {

	/**
	 * 根据订单id查找订单项
	 * @param oid 订单id
	 * @return 该订单id下的所有订单项
	 */
	@Select("select itemid,sub_price subtotal,quantity count,pid,oid from orderitem where oid = #{oid}")
	List<OrderItem> findItemsByOid(@Param("oid") String oid);
}
