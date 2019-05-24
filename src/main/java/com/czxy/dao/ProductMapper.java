package com.czxy.dao;

import com.czxy.domain.Product;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductMapper extends Mapper<Product> {

    @Select("select * from product where cid = #{cid}")
    List<Product> findProByCid(String cid);
}
