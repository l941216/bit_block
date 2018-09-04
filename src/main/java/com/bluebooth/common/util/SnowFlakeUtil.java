package com.bluebooth.common.util;

public class SnowFlakeUtil {
    /**
     * @BelongsPackage: com.bluebooth.common.util
     * @author: Lee
     * @CreateDate: 2018/09/04/9:05
     * @version: 1.0
     * @Description: 通过nextId雪花算法获取唯一值
     */
        private  static final long twepoch = 1535990400000L;
        private static final long workerIdBits = 5L;
        private static final long datacenterIdBits = 5L;
        private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
        private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
        private static final long sequenceBits = 12L;
        private static final long workerIdShift = sequenceBits;
        private static final long datacenterIdShift = sequenceBits + workerIdBits;
        private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
        private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

        private static long workerId;
        private static long datacenterId;
        private static long sequence = 0L;
        private static long lastTimestamp = -1L;

        public SnowFlakeUtil(long workerId, long datacenterId) {
            if (workerId > maxWorkerId || workerId < 0) {
                throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
            }
            if (datacenterId > maxDatacenterId || datacenterId < 0) {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
            }
            this.workerId = workerId;
            this.datacenterId = datacenterId;
        }

        public static synchronized  long nextId() {
            long timestamp = timeGen();
            if (timestamp < lastTimestamp) {
                throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
            }
            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1) & sequenceMask;
                if (sequence == 0) {
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0L;
            }

            lastTimestamp = timestamp;

            return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
        }

        protected static long tilNextMillis(long lastTimestamp) {
            long timestamp = timeGen();
            while (timestamp <= lastTimestamp) {
                timestamp = timeGen();
            }
            return timestamp;
        }

        protected static long timeGen() {
            return System.currentTimeMillis();
        }

//        public static void main(String[] args) {
//            com.bluebooth.common.util.SnowFlakeUtil idWorker = new com.bluebooth.common.util.SnowFlakeUtil(0, 0);
//            for (int i = 0; i < 1000; i++) {
//                long id = idWorker.nextId();
//                System.out.println(id);
//            }
//        }
    }
