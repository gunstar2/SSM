package com.ron.ssm.service;

import com.ron.ssm.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IProductService {
    /**
     * 查询所有的产品信息
     * @return
     */
    public List<Product> findAll(int page, int size) throws Exception;

    /**
     * 添加方法
     * @param product
     * @throws Exception
     */
    void save(Product product)throws Exception;
}
