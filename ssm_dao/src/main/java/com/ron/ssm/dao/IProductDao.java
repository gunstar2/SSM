package com.ron.ssm.dao;


import com.ron.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {
    /**
     * 查询所有的产品信息
     * @return
     */
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    @Insert("insert into product(productNum,productName,cityName," +
            "departureTime,productPrice,productDesc,productStatus)" +
            "values(#{productNum},#{productName},#{cityName}," +
            "#{departureTime},#{productPrice}," +
            "#{productDesc},#{productStatus})")
    /**
     * 添加方法
     */
    void save(Product product)throws Exception;


    /**
     * 根据id查询
     */

    @Select("select * from product where id = #{id}")
    Product findById(String id)throws Exception;
}
