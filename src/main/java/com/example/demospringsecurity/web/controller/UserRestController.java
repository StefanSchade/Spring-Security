package com.example.demospringsecurity.web.controller;

import com.example.demospringsecurity.core.dto.UserDto;
import com.example.demospringsecurity.core.model.request.UserDetailsRequestModel;
import com.example.demospringsecurity.core.model.response.UserRest;
import com.example.demospringsecurity.core.repositories.UserRepository;
import com.example.demospringsecurity.web.service.UserService;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
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

    @GetMapping(path = "/{userId}/")
    public UserRest getUser(@PathVariable String userId) {
        UserRest returnValue = new UserRest();
        UserDto userDto = userService.getUserByUserId(userId);
        BeanUtils.copyProperties(userDto,returnValue);
        return returnValue;
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        log.info("Post Request received " + userDetails);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);
        UserDto createdUser =  userService.createUser(userDto);
        UserRest returnValue = new UserRest();
        BeanUtils.copyProperties(createdUser, returnValue);
        return returnValue;
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