package com.sc.act.api.commons.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@ConditionalOnProperty(prefix = "swagger2", name = "enable", havingValue = "true")
@EnableSwagger2
public class Swagger2Configuration {

    @Value("${spring.application.name:}")
    private String appName;

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.buildApiInfo())
                .globalOperationParameters(new ArrayList<>())
                .directModelSubstitute(Byte.class, Integer.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sc.act.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("使用 Swagger2 构建RESTful API" + this.appName)
                .version("1.0.0")
                .description("API 描述")
                .build();
    }

}
