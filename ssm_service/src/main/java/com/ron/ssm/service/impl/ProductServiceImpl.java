package com.ron.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ron.ssm.dao.IProductDao;
import com.ron.ssm.domain.Product;
import com.ron.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl  implements IProductService{
    @Autowired
    private IProductDao dao;


    /**
     * 查询所有的产品信息
     * @return
     */
    @Override
    public List<Product> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return dao.findAll();
    }

    /**
     * 添加方法
     * @param product
     * @throws Exception
     */
    @Override
    public void save(Product product) throws Exception {
        dao.save(product);
    }
}
