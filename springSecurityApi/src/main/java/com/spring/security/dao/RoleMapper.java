package com.spring.security.dao;

import com.spring.security.dto.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author
 * @version 1.0 2016/10/11
 * @description
 */
@Mapper
public interface RoleMapper {

    /**
     * 根据用户ID获取用户角色
     * @param userId
     * @return
     */
    public List<Role> getUserRole(Integer userId);
}
