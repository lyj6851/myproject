package com.hhf.common.idworker.snowflake;

/**
 * 分布式id生成
 * @author huhaifeng
 *
 */
public class SequenceService {
    private final IdWorker idWorker;

    public SequenceService(IdWorker idWorker) {
        this.idWorker = idWorker;
    }

    public long nextId() {
        return this.idWorker.getId();
    }
}
