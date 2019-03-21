package com.hhf.common.idworker.snowflake.workerId;

import java.util.Random;

public interface WorkerIdFactory {
    int getWorkerId();

    String getWorkerIdType();
}
