package com.spring.security.dao;

import com.spring.security.dto.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author
 * @version 1.0 2016/10/11
 * @description
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    public UserInfo getUserByName(String userName);
}
