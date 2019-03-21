package com.hhf.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author huhaifeng
 * @Description  获取Spring上下文实例
 */
@Component("springContextHolder")
public class SpringContextHolder implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);
    public static final String BEAN_NAME = "springContextHolder";
    private static ApplicationContext applicationContext;

    public SpringContextHolder() {
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <O> O getBean(String name, Class<O> requiredType) {
        return getApplicationContext().getBean(name, requiredType);
    }

    public static <O> O getBean(Class<O> requiredType) {
        return getApplicationContext().getBean(requiredType);
    }

    public static <O> String[] getBeanNamesForType(Class<O> requiredType) {
        return getApplicationContext().getBeanNamesForType(requiredType);
    }

    public static <O> Map<String, O> getBeansForType(Class<O> requiredType) {
        return getApplicationContext().getBeansOfType(requiredType);
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        if (applicationContext == null) {
            applicationContext = arg0;
            logger.info("开始设置SpringContextHolder.applicationContext");
        }

    }
}
