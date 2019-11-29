package com.example.demospringsecurity.core.bootstrap;

import com.example.demospringsecurity.core.domain.UserEntity;
import com.example.demospringsecurity.core.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("load test data into db");

        UserEntity fabian = new UserEntity("abc","Fabian", "Leuk", "fabian.leuk@msg-gillardon.de", "abc");
        UserEntity stefan = new UserEntity("abc1","Stefan", "Schade", "stefan.schade@msg-gillardon.de", "def");
        UserEntity mazda = new UserEntity("abc2","Mazda", "Saadi", "Mazda.Fahandezh.Saadi@msg-gillardon.de", "dfw");

        userRepository.save(fabian);
        userRepository.save(stefan);
        userRepository.save(mazda);
    }
}
