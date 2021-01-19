package com.ron.ssm.service;

import com.ron.ssm.domain.Permission;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IPermissionService {


    List<Permission> findAll() throws Exception;


    void save(Permission permission) throws Exception;

    Permission findById(String id) throws Exception;

    void deleteById(String id) throws Exception;
}
