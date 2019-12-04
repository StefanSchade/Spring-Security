package com.example.demospringsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Arrays;

@Slf4j
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT; // must this be static?

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }

    public static Object getBean(String beanName) {

        if(CONTEXT == null ) {
            log.info("Context was not autowired");} else {
            log.info("Context was autowired");
            log.info("List of Beans" + String.valueOf(Arrays.asList(CONTEXT.getBeanDefinitionNames())));
        }

        return CONTEXT.getBean(beanName);
    }
}
