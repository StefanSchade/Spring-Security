package com.example.demospringsecurity.core.model.response;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class UserRest {

    public String userId;
    private String firstName;
    private String lastName;
    private String email;

}
