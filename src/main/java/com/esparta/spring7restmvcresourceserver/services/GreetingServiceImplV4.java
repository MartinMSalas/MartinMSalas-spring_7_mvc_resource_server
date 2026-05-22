package com.esparta.spring7restmvcresourceserver.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/*
 * Author: M
 * Date: 23-Jan-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */
@Profile({"dev", "default"})
@Service("GreetingServiceImpl")
public class GreetingServiceImplV4 implements GreetingService {
    @Override
    public String sayGreeting() {
        return "dev - Hello from GreetingServiceImpl base Service";
    }
}
