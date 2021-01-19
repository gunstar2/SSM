package com.ron.ssm.service.impl;

import com.ron.ssm.dao.IRoleDao;
import com.ron.ssm.domain.Permission;
import com.ron.ssm.domain.Role;
import com.ron.ssm.domain.RoleIdAndPermissionId;
import com.ron.ssm.service.IRoleService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RoleServiceImpl implements IRoleService {


    @Autowired
    private IRoleDao dao;

    @Override
    public List<Role> findAll() throws Exception {

        return dao.findAll();
    }

    @Override
    public void save(Role role) {
        dao.save(role);
    }

    @Override
    public Role findByid(String id) throws Exception {
        return dao.findById(id);
    }

    @Override
    public void deleteRoleById(String id) throws Exception {

        //从role_premission中删除
        dao.deleteFromPremission(id);



        //从User_role中删除
        dao.deleteFromUser(id);


        //删除用户
        dao.deleteRoleById(id);
    }

    @Override
    public List<Permission> findPermission(String roleId)throws  Exception {
        return dao.findPermission(roleId);
    }

    @Override
    public void addPermission(RoleIdAndPermissionId rp) throws Exception {
        dao.addPermission(rp);
    }
}
