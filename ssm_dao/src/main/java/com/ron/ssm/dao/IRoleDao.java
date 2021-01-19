package com.ron.ssm.dao;

import com.ron.ssm.domain.Permission;
import com.ron.ssm.domain.Role;
import com.ron.ssm.domain.RoleIdAndPermissionId;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //根据用户id查询出对应的角色
    @Select("select * from role where id in (select roleid from users_role where userid = #{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,
            many = @Many(select = "com.ron.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(String id) throws Exception;


    @Select("select * from role")
    List<Role> findAll()throws Exception;

    @Insert("insert into role(ROLENAME,ROLEDESC) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from  role where id in " +
            "(select ROLEID from  role_permission where PERMISSIONID = #{id})")
    List<Role> findRoleByPerssionId(String id) throws Exception;


    @Select("select * from role where id =#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.ron.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(String id) throws  Exception;


    //从User_role中删除
    @Delete("delete from users_role where ROLEID = #{id}")
    void deleteFromUser(String id)throws Exception;


    //从role_premission中删除
    @Delete("delete from users_role where ROLEID  = #{id}")
    void deleteFromPremission(String id)throws Exception;

    //删除用户
    @Delete("delete from role where id = #{id}")
    void deleteRoleById(String id)throws Exception;

    @Select("select * from permission where  id not in " +
            "(select PERMISSIONID from role_permission where ROLEID =#{roleId})")
    List<Permission> findPermission(String roleId);

    @Insert("insert into role_permission(ROLEID,PERMISSIONID) values(#{roleId},#{permissionId})")
    void addPermission(RoleIdAndPermissionId rp)throws Exception;
}
