package com.hhf.app;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableScheduling
@EnableTransactionManagement(proxyTargetClass=true)
//exclude={JpaRepositoriesAutoConfiguration.class} //禁止springboot自动加载持久化bean
@SpringBootApplication(scanBasePackages={"com.hhf.**","com.fndsoft.flowable"})  //scanBasePackages={"com.hhf.**"}  配合MapperScan扫描mapper
@MapperScan(value = {"com.hhf.**.mapper"})
@EnableSwagger2
//@ComponentScan(value = {"com.hhf.demo.mapper"})
public class MyServerApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyServerApplication.class);

    public static void main(String[] args) {

        ApplicationContext app = SpringApplication.run(MyServerApplication.class, args);
        LOGGER.info("============= SpringBoot Start Success =============");
    }
}
