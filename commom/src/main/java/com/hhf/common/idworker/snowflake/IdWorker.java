package com.hhf.common.idworker.snowflake;

import com.hhf.common.idworker.snowflake.workerId.WorkerIdFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IdWorker {
    private static Logger logger = LoggerFactory.getLogger(IdWorker.class);
    private static final long epoch = 1451577600000L;
    private static final int WORKER_ID_BITS = 10;
    private static final long defaultRefreshTimeAfterNTP = 1L;
    private static final int SEQUENCE_BITS = 12;
    private static final int WORKER_ID_SHIFT = 12;
    private static final int TIMESTAMP_SHIFT = 22;
    private static final long MAX_WORKER_ID = 1023L;
    private static final long MAX_SEQUENCE = 4095L;
    private final long refreshTimeAfterNTP;
    private long sequence;
    private long lastTimestamp;
    private long workerId;
    private boolean isInited;
    private boolean useIP;
    private WorkerIdFactory workerIdFactory;
    private static int ip;
    private static final Logger log = LoggerFactory.getLogger(IdWorker.class);

    private static int toInt(byte[] bytes) {
        int result = 0;

        for(int i = 0; i < 4; ++i) {
            result = result << 8 | bytes[i] & 255;
        }

        return Math.abs(result);
    }

    public long getEpoch() {
        return 1451577600000L;
    }

    public long getWorkerId() {
        return this.workerId;
    }

    public void setWorkerID(long workerID) {
        this.workerId = workerID;
    }

    public long getLastTimestamp() {
        return this.lastTimestamp;
    }

    public long getId() {
        return this.nextId();
    }

    IdWorker(WorkerIdFactory workerIdFactory) {
        this(1L, workerIdFactory);
    }

    IdWorker(long refreshTimeAfterNTP, WorkerIdFactory workerIdFactory) {
        this.sequence = 0L;
        this.lastTimestamp = 0L;
        this.isInited = false;
        this.refreshTimeAfterNTP = refreshTimeAfterNTP;
        this.workerIdFactory = workerIdFactory;
        boolean var4 = false;

        int ipadd;
        try {
            InetAddress address = InetAddressHelper.getLocalHostLANAddress();
            ipadd = toInt(address.getAddress());
        } catch (UnknownHostException var6) {
            logger.info(var6.getMessage());
            throw new RuntimeException("Could not get Host.", var6);
        }

        ip = ipadd % 1000;
    }

    private synchronized long nextId() {
        if (!this.isInited) {
            this.workerId = (long)this.workerIdFactory.getWorkerId();
            if (this.workerId < 0L) {
                return 0L;
            }
        }

        long timestamp = timeGen();
        if (timestamp == this.lastTimestamp) {
            this.sequence = this.sequence + 1L & 4095L;
            if (this.sequence == 0L) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            if (timestamp < this.lastTimestamp) {
                timestamp = this.afterNTP(this.lastTimestamp);
            }

            this.sequence = 0L;
        }

        this.lastTimestamp = timestamp;
        return timestamp - 1451577600000L << 22 | this.workerId << 12 | this.sequence;
    }

    private static long timeGen() {
        return System.currentTimeMillis();
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = timeGen(); timestamp == lastTimestamp; timestamp = timeGen()) {
            ;
        }

        if (timestamp < lastTimestamp) {
            timestamp = this.afterNTP(this.lastTimestamp);
        }

        return timestamp;
    }

    private long afterNTP(long lastTimestamp) {
        long timestamp;
        for(timestamp = timeGen(); timestamp <= lastTimestamp; timestamp = timeGen()) {
            try {
                Thread.sleep(this.refreshTimeAfterNTP);
            } catch (InterruptedException var6) {
                logger.warn("Interrupted!", var6);
                Thread.currentThread().interrupt();
            }
        }

        return timestamp;
    }

    public boolean isUseIP() {
        return this.useIP;
    }

    public void setUseIP(boolean useIP) {
        this.useIP = useIP;
    }
}
