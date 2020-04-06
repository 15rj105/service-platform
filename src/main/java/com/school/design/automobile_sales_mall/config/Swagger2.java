package com.school.design.automobile_sales_mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) //api信息
                .select()   //构建api选择器
                .apis(RequestHandlerSelectors.basePackage("com.school.design.automobile_sales_mall.controller")) //api选择器选择api的包
                .paths(PathSelectors.any()) //api选择器选择包路径下任何api显示在文档中
                .build();  //创建文档
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("校园服务平台")
                .description("接口描述")
                .termsOfServiceUrl("termsOfServiceUrl")
                .contact("new contact")
                .version("1.0")
                .build();
    }
}
