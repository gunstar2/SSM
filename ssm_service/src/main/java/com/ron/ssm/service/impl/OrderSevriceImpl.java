package com.ron.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ron.ssm.dao.IOrdersDao;
import com.ron.ssm.domain.Orders;
import com.ron.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderSevriceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao ordersDao;
    @Override
    public List<Orders> findAll() throws Exception {

        return  ordersDao.findAll();

    }

    @Override
    public List<Orders> findAll(int page, int size)throws Exception {
        //pageNum为页码值，pageSize为每页条数
        PageHelper.startPage(page,size);
        return  ordersDao.findAll();
    }

    @Override
    public Orders findByid(String ordersId) {
        return ordersDao.findById(ordersId);
    }
}
