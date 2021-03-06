package com.ron.ssm.dao;

import com.ron.ssm.domain.Role;
import com.ron.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {


    //登录
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.ron.ssm.dao.IRoleDao.findRoleByUserId")),

    })
    UserInfo findByUsername(String username) throws Exception;


    //查询所有用户
    @Select("select * from users")
    List<UserInfo> findAll() throws  Exception;

    //添加用户
    @Insert("insert into users(email,username,password,phoneNum,status) " +
            "values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    //查询用户详情信息
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.ron.ssm.dao.IRoleDao.findRoleByUserId")),

    })
    UserInfo findById(String id)throws Exception;


    @Select("select * from Role where id not in" +
            " (Select ROLEID from users_role where USERID = #{userId})")
    List<Role> finOtherRoles(String userId)throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);
}
