package com.ron.ssm.service;

import com.ron.ssm.domain.Permission;
import com.ron.ssm.domain.Role;
import com.ron.ssm.domain.RoleIdAndPermissionId;

import java.util.List;

public interface IRoleService {




    List<Role> findAll()throws  Exception;


    void save(Role role)throws  Exception;

    Role findByid(String id)throws  Exception;

    void deleteRoleById(String id)throws  Exception;

    List<Permission> findPermission(String roleId)throws  Exception;

    void addPermission(RoleIdAndPermissionId rp)throws  Exception;
}
