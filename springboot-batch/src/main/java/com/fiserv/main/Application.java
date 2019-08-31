package com.fiserv.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.fiserv.batch.BatchConfiguration;

/**
 * The Class Application.
 * 
 * @author ashraf	
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer
{

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BatchConfiguration.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(
      SpringApplicationBuilder builder) {
        return builder.sources(BatchConfiguration.class);
    }
}
