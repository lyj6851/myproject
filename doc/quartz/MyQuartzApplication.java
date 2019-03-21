package com.hhf.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass=true)
@SpringBootApplication  //scanBasePackages={"com.hhf.**"}  配合MapperScan扫描mapper
@EntityScan(basePackages = {"com.hhf"})//指定Entity的扫描路径
public class MyQuartzApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyQuartzApplication.class);

    public static void main(String[] args) {

        ApplicationContext app = SpringApplication.run(MyQuartzApplication.class, args);
        LOGGER.info("============= SpringBoot Start Success =============");
    }
}
