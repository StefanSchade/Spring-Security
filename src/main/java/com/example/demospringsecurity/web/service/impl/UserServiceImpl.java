package com.example.demospringsecurity.web.service.impl;

import com.example.demospringsecurity.core.domain.UserEntity;
import com.example.demospringsecurity.core.dto.UserDto;
import com.example.demospringsecurity.core.repositories.UserRepository;
import com.example.demospringsecurity.core.shared.Utils;
import com.example.demospringsecurity.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {
        UserEntity userEntity = new UserEntity();
        if(userRepository.findByEmail(user.getEmail())!=null) throw new RuntimeException("Record already exists");
        // TODO dummy values prevent exception due to non-nullable fields, they have to be generated later on
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        String publicUserId = utils.generateUserId(30);
        user.setUserId(publicUserId);
        BeanUtils.copyProperties(user, userEntity); // error if filed  null that is marked as non-null
        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDto returnvalue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails,returnvalue);

        return returnvalue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
