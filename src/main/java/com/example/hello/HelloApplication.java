package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.example.hello.random.RealRandom;

@SpringBootApplication
public class HelloApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HelloApplication.class);
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx 
				= SpringApplication.run(HelloApplication.class, args);
			System.out.println("==============================");
			System.out.println(ctx.getBeanDefinitionCount());
			String[] names = ctx.getBeanDefinitionNames();
			for (String name : names) {
				System.out.printf("%s\n", name);
			}
			
			RealRandom realRandom1 
			     = (RealRandom) ctx.getBean("realRandom");
			realRandom1.number = "From 1";
			
			RealRandom realRandom2
		     = (RealRandom) ctx.getBean("realRandom");
			realRandom2.number = "From 2";
			
			System.out.println(realRandom1.number);
			System.out.println(realRandom2.number);
			System.out.println("==============================");
			
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
