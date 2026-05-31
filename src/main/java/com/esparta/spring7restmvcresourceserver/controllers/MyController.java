package com.esparta.spring7restmvcresourceserver.controllers;

/*
 * Author: M
 * Date: 23-Jan-26
 * Project Name: spring-7-rest-mvc-resource-server
 * Description: beExcellent
 */

import com.esparta.spring7restmvcresourceserver.services.GreetingService;
import org.springframework.stereotype.Controller;

//@Controller
public class MyController {

    private final GreetingService  greetingService;

    public MyController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }


    public String sayHello() {
        System.out.println("In MyController, sayHello()");

        return greetingService.sayGreeting();
    }
}
