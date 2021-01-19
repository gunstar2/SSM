package com.ron.ssm.dao;

import com.ron.ssm.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPermissionDao {


    @Select("select * from  Permission " +
            "where id in (select permissionid from role_permission where roleId = #{roleId})")
    public List<Permission> findPermissionByRoleId(String roleId)throws  Exception;


    @Select("select * from  Permission")
    List<Permission> findAll()throws  Exception;


    @Insert("insert into Permission(permissionName,URL) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;


    @Select("select * from  Permission where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "permissionName",column = "permissionName"),
            @Result(property = "url",column = "url"),
            @Result(property = "roles",column = "id" ,javaType = java.util.List.class,
                    many=@Many(select = "com.ron.ssm.dao.IRoleDao.findRoleByPerssionId"))
    })
    Permission findById(String id);

    //根据id删除权限
    @Delete("delete from permission where id = #{id}")
    void deleteFromPermission(String id)throws Exception;
    //根据id从权限,角色关联表中删除数据
    @Delete("delete from role_permission where PERMISSIONID = #{id}")
    void deleteFromRole_permission(String id)throws Exception;
}
