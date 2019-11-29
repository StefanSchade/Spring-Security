package com.example.demospringsecurity.web.service;

import com.example.demospringsecurity.core.dto.UserDto;
import org.springframework.stereotype.Service;

//import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public interface UserService
//        extends UserDetailsService
{
    UserDto createUser(UserDto user);
}
