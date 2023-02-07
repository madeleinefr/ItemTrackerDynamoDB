package com.aws.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
*@SpringBootApplication:
 * enables the auto-configuration mechanism of Spring Boot, a component scan which scans the
 * sub-packages of the package where the application is located, and allows to
 * import additional configuration classes or to define extra configurations
 * it is basically the equivalent for using
 * @Configuration,
 * @EnableConfiguration
 * and @ComponentScan in just one annotation
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) throws Throwable {
        SpringApplication.run(App.class, args);
    }
}