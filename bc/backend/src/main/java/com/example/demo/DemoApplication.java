package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DemoApplication {

	/**
	 * The entry point of the application.
	 *
	 * This method starts the Spring application by invoking the
	 * {@link SpringApplication#run(Class, String...)} method with the
	 * specified application class and command-line arguments.
	 *
	 * @param args command-line arguments passed to the application
	 * @throws IllegalArgumentException if the application class is null
	 * @throws Exception if the application fails to start due to any reason
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
