package com.ron.ssm.service.impl;

import com.ron.ssm.dao.IUserDao;
import com.ron.ssm.domain.Role;
import com.ron.ssm.domain.UserInfo;
import com.ron.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    //加密类
    @Autowired
    private BCryptPasswordEncoder encoder;
    //登录
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo info = null;
        try {
            info= userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //自己的用户对象,和封装成UserDetails对象
        /*User user = new User(info.getUsername(),"{noop}"+info.getPassword()
                ,getAuthority(info.getRoles()));//参数:账号,密码,权限集合*/
                    //new User(info.getUsername(),"{noop}"+info.getPassword()//不用加密的方法,要使用这种
        User user = new User(info.getUsername(),info.getPassword(),//对方法加密使用这种
                info.getStatus()==0?false:true,//状态是否开启
                true,true,true,//是否有效,是否有资格,是否锁定
                getAuthority(info.getRoles()));//权限


        return user;
    }

    //权限方法
    public Collection<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        //roles为为我们自己User表里的角色集合
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();//创建一个权限集合
        for (Role role : roles) {
            //SimpleGrantedAuthority就是一个权限对象
            //我们每个角色对应一种权限
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return list;
    }


    //查询所有用户
    @Override
    public List<UserInfo> findAll() throws Exception {

        return userDao.findAll() ;
    }

    //保存用户
    @Override
    public void save(UserInfo userInfo) throws Exception {
        //对密码加密处理
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));

        userDao.save(userInfo);
    }

    //查询用户详情
    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);

    }

    @Override
    public List<Role> finOtherRoles(String userId) throws Exception {
        return userDao.finOtherRoles(userId);
    }

    //给用户添加角色
    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
