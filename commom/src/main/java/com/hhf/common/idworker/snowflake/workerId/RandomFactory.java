package com.hhf.common.idworker.snowflake.workerId;

import java.util.Random;

public class RandomFactory implements WorkerIdFactory {
    public RandomFactory() {
    }

    @Override
    public int getWorkerId() {
        Random random = new Random();
        return random.nextInt(1024);
    }

    @Override
    public String getWorkerIdType() {
        return "RANDOM";
    }
}