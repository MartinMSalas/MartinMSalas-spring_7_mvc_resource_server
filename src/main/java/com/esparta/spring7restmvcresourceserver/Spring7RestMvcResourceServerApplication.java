package com.esparta.spring7restmvcresourceserver;


import com.esparta.spring7restmvcresourceserver.controllers.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class Spring7RestMvcResourceServerApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(Spring7RestMvcResourceServerApplication.class, args);
/*
		MyController controller = ctx.getBean(MyController.class);

		System.out.println("In main: " + controller);
		System.out.println(controller.sayHello());

 */
	}

}
