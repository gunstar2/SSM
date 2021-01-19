package com.ron.ssm.service.impl;

import com.ron.ssm.dao.IPermissionDao;
import com.ron.ssm.domain.Permission;
import com.ron.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) throws Exception {
        return permissionDao.findById(id);
    }

    @Override
    public void deleteById(String id)throws Exception {
        //从权限,角色关联表中删除数据
        permissionDao.deleteFromRole_permission(id);
        //删除数据
        permissionDao.deleteFromPermission(id);
    }
}
