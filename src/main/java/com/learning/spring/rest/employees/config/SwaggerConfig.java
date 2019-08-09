package com.learning.spring.rest.employees.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.learning.spring.rest.employees.controller"))
                .paths(paths()).build()
                .apiInfo(metaInfo());
    }

    List<VendorExtension> vendorExtensions = new ArrayList<>();
    Contact contact = new Contact("CRISTEA Danut", "www.java.com", "danut.cristea@softvision.ro");

    private ApiInfo metaInfo() {
        return new ApiInfo("Employees Management App", "Manage employees, projects", "1.0",
                "Terms of Service", contact,
                "Apache License", "", vendorExtensions);
    }

    private Predicate<String> paths() {
        return or(
                regex("/community.*"),
                regex("/communities"),
                regex("/employee.*"),
                regex("/employees"),
                regex("/register.*"),
                regex("/search"),
                regex("/getEmployees*")


        );
    }

}
