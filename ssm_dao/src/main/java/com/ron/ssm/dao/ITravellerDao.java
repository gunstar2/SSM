package com.ron.ssm.dao;

import com.ron.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {


    @Select("select * from traveller where id  in (select travellerid from order_traveller where orderid=#{ordersId})")
    public List<Traveller> findByOrdersId(String ordersId)throws Exception;
}
