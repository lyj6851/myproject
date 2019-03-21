package com.hhf.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author huhaifeng
 * @description  swagger2 相关配置
 * @EnableSwagger2  //注解开启 swagger2 功能
 * @Configuration  //注解标示,这是一个配置类,@Configuation注解包含了@Component注解
 * 可以不用在使用@Component注解标记这是个bean了
 */

@Configuration
public class SwaggerConfig {

    private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);
    /**
     * 通过 createRestApi函数来构建一个DocketBean
     * 函数名,可以随意命名,喜欢什么命名就什么命名
     */
    @Bean
    public Docket createRestApi() {
        log.info("start Swagger2 config");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .select()
                //控制暴露出去的路径下的实例
                //如果某个接口不想暴露,可以使用以下注解
                //@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                .apis(RequestHandlerSelectors.basePackage("com.hhf"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("SpringBoot多模块后台服务")
                //创建人
                .contact("胡海丰")
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }

}
