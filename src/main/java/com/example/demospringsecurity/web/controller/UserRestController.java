package com.example.demospringsecurity.web.controller;

import com.example.demospringsecurity.core.dto.UserDto;
import com.example.demospringsecurity.core.model.request.UserDetailsRequestModel;
import com.example.demospringsecurity.core.model.response.UserRest;
import com.example.demospringsecurity.core.repositories.UserRepository;
import com.example.demospringsecurity.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getUser() {
        return "getUser called";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        log.info("Post Request received " + userDetails);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);
        UserDto createdUser =  userService.createUser(userDto);
        UserRest returnvalue = new UserRest();
        BeanUtils.copyProperties(createdUser, returnvalue);
        return returnvalue;
    }

    @PutMapping
    public String updateUser() {
        return "updateUser called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "deleteUser called";
    }
}
