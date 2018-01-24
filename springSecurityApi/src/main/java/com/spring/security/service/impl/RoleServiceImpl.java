package com.spring.security.service.impl;

import com.spring.security.dao.RoleMapper;
import com.spring.security.dto.Role;
import com.spring.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @version 1.0 2016/10/11
 * @description
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getUserRole(Integer userId) {
        List<Role> roles = roleMapper.getUserRole(userId);
        return roles;
    }
}
