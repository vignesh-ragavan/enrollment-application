package com.vignesh.enrollmentapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EnrollmentapplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnrollmentapplicationApplication.class, args);
    }

}
