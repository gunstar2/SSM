package com.ron.ssm.service;

import com.ron.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {
    List<Orders> findAll()throws  Exception;

    List<Orders> findAll(int page, int size) throws Exception;

    Orders findByid(String ordersId);
}
