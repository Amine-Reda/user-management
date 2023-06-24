package com.project.usermanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    public static final String USER_CONTROLLER_TAG = "User api resource";
    public static final String RIGHT_CONTROLLER_TAG = "Right api resource";

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.project.usermanagement"))
                .paths(PathSelectors.any())
                .build()
                .tags(
                        new Tag(USER_CONTROLLER_TAG, "API for user crud operations"),
                        new Tag(RIGHT_CONTROLLER_TAG, "API for right crud operations")
                );
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "UserManagement REST API",
                "Controllers documentation",
                "2.0",
                "Terms of service",
                new Contact("UserManagement Team", "www.userManagement.com", "amine.reda037@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

}
