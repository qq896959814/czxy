package com.czxy.bookstore.dao;

import com.czxy.bookstore.domain.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by 刘正风 on 2019/5/23.
 */
public interface ProductMapper extends Mapper<Product>{

    @Select("select * from product where is_hot = 1")
    List<Product> findHotProducts();

    @Select("select * from product order by pdate DESC ")
    List<Product> findLastProducts();

    @Select("select * from product where cid = #{cid}")
    List<Product> findProductByCid(@Param("cid") String cid);
}
