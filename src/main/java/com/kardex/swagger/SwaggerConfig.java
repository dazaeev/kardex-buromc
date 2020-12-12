package com.kardex.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kardex.rest.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "BeITalent Microservices",
                "API for BuróMc",
                "1.0",
                "Terms of service",
                new Contact("Nazario Dazaeev", "https://www.buromc.com", "ngonzalez@buromc.com"),
               "Apache Tomcat",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}