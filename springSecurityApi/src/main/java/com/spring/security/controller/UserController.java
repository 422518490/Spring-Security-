package com.spring.security.controller;

import com.spring.security.dto.UserInfo;
import com.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author
 * @version 1.0 2016/10/10
 * @description
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public UserInfo getUserInfo(UserInfo user){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("LIQIN");
        userInfo.setPassword("123");
        return userInfo;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public UserInfo login(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("LIQIN");
        //userInfo.setPassword("123");
        return userInfo;
    }

    @RequestMapping(value = "/getAuth",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public UserInfo getAuth(UserInfo user){
        UserInfo userInfo = new UserInfo();
        //userInfo.setUserName("LIQIN");
        userInfo.setPassword("123");
        return userInfo;
    }

    @RequestMapping(value = "/auth", method=RequestMethod.GET)
    public UserInfo getAuth(Principal principal)  {
        return userService.getUserByName(principal.getName());
    }
}
