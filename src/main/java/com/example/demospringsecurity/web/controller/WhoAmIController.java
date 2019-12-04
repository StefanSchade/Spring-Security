package com.example.demospringsecurity.web.controller;

import com.example.demospringsecurity.core.domain.UserEntity;
import com.example.demospringsecurity.core.dto.UserDto;
import com.example.demospringsecurity.core.model.response.UserRest;
import com.example.demospringsecurity.core.repositories.UserRepository;
import com.example.demospringsecurity.security.SecurityConstants;
import com.example.demospringsecurity.web.service.UserService;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("whoami")
public class WhoAmIController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public UserRest greetUser(@RequestHeader("Authorization") String auth) {

        auth = auth.replace(SecurityConstants.TOKEN_PREFIX, "");

        String userEmail = Jwts.parser()
                .setSigningKey(SecurityConstants.getTokenSecret())
                .parseClaimsJws(auth)
                .getBody()
                .getSubject();

        UserDto userDto = userService.getUser(userEmail);
        UserRest returnValue = new UserRest();
        BeanUtils.copyProperties(userDto,returnValue);
        return returnValue;
    }


}
