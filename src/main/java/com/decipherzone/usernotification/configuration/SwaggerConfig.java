package com.decipherzone.usernotification.configuration;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/** swagger-ui url : http://localhost:8080/swagger-ui.html#/ */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "User-Notification Application",
                "The application provide following : \n" +
                        "\t1. API to perform CRUD operations on both User and Notification\n" +
                        "\t2. API to send notification \n" +
                        "\t3. API to Enable/Disable a notification \n" +
                        "\t4. API to get notification list as per following: type, date_passed, userId",
                "1.0",
                "",
                new Contact("Rohit Jangid","<developer-url>","03rohitjangid@gmail.com"),
                "License","<license-url>", Collections.emptyList());
    }
}
