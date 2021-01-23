package com.venns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class SwaggerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerDemoApplication.class, args);
    }

}
