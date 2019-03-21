package com.hhf.common.idworker.snowflake;

import com.hhf.common.idworker.snowflake.workerId.RandomFactory;
import com.hhf.common.idworker.snowflake.workerId.WorkerIdFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

@Configuration("RandomIdWorkerAutoConfig")
@EnableConfigurationProperties
public class RandomIdWorkerAutoConfig {
    public RandomIdWorkerAutoConfig() {
    }

    @Bean({"randomIdWorker"})
    @DependsOn({"randomWorkerIdFactory"})
    @Lazy
    IdWorker idWorker(WorkerIdFactory workerIdFactory) {
        return new IdWorker(workerIdFactory);
    }

    @Bean({"randomSequenceService"})
    @DependsOn({"randomIdWorker"})
    @Lazy
    SequenceService sequenceService(IdWorker idWorker) {
        return new SequenceService(idWorker);
    }

    @Bean({"randomWorkerIdFactory"})
    WorkerIdFactory randomFactory() {
        WorkerIdFactory workerIdFactory = new RandomFactory();
        return workerIdFactory;
    }
}