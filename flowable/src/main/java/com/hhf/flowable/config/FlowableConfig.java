package com.hhf.flowable.config;

import com.fndsoft.flowable.service.ListenerService;
import com.hhf.flowable.listener.FlowableListenerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: PGL
 * @Date: 2018/10/9
 * @Time: 10:14
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
public class FlowableConfig {
    @Bean
    public ListenerService getListenerService() {
        return new FlowableListenerServiceImpl();
    }
}
