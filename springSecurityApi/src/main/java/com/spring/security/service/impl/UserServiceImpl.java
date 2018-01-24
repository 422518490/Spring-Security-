package com.spring.security.service.impl;

import com.spring.security.dao.UserMapper;
import com.spring.security.dto.Role;
import com.spring.security.dto.UserInfo;
import com.spring.security.service.RoleService;
import com.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version 1.0 2016/10/11
 * @description
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserInfo userInfo = getUserByName(userName);
        if (userInfo == null) {
            throw new UsernameNotFoundException(userName);
        }

        //根据用户获取用户角色
        List<Role> roles = roleService.getUserRole(userInfo.getUserId());
        //定义权限集合
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        //添加权限到集合中
        for (Role role : roles){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleType()));
        }
        boolean booleanStatus = true;
        if(userInfo.getStatus() == 0){
            booleanStatus = false;
        }
        //加密密码
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
        User user = new User(userInfo.getUserName(),bCryptPasswordEncoder.encode(userInfo.getPassword()),booleanStatus,true,true, true, grantedAuthorities);
        return user;
    }

    @Override
    public UserInfo getUserByName(String userName) {
        UserInfo userInfo = userMapper.getUserByName(userName);
        return userInfo;
    }
}
