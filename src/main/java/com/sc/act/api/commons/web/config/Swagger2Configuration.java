package com.sc.act.api.commons.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
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
//        ParameterBuilder plateformParameterBuilder = new ParameterBuilder();
//        plateformParameterBuilder
//                .name("platform")
//                .parameterType("header")
//                .defaultValue("10")
//                .description("平台")
//                .modelRef(new ModelRef("string"))
//                .required(false).build();
//        ParameterBuilder languageParameterBuilder = new ParameterBuilder();
//        languageParameterBuilder
//                .name("language")
//                .parameterType("header")
//                .defaultValue("zh")
//                .description("语言：中文（zh） 英文（en）")
//                .modelRef(new ModelRef("string"))
//                .required(false).build();
//        ParameterBuilder tokenParameterBuilder = new ParameterBuilder();
//        tokenParameterBuilder.name("token")
//                .parameterType("header")
//                .defaultValue("")
//                .description("用户token,登录后接口必传")
//                .modelRef(new ModelRef("string"))
//                .required(false)
//                .build();
//        ParameterBuilder authorizationParameterBuilder = new ParameterBuilder();
//        authorizationParameterBuilder.name("authorization")
//                .parameterType("header")
//                .defaultValue("")
//                .description("B端authorization,登录后接口必传")
//                .modelRef(new ModelRef("string"))
//                .required(false).build();
//        List<Parameter> aParameters = new ArrayList<>();
//         .globalOperationParameters(aParameters)
//        aParameters.add(plateformParameterBuilder.build());
//        aParameters.add(languageParameterBuilder.build());
//        aParameters.add(tokenParameterBuilder.build());
//        aParameters.add(authorizationParameterBuilder.build());
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
