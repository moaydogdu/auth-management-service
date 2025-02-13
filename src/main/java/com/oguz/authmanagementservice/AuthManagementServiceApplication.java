package com.oguz.authmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.oguz.authmanagementservice.auth.model.config")
public class AuthManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthManagementServiceApplication.class, args);
    }

}
