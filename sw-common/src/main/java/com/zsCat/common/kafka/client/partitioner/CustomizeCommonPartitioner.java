package com.zsCat.common.kafka.client.partitioner;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * 自定义的通用分区器
 * Created by likaige on 2016-06-03.
 */
public class CustomizeCommonPartitioner implements Partitioner {
    public CustomizeCommonPartitioner(VerifiableProperties props) {
    }

    @Override
    public int partition(Object key, int numPartitions) {
        return (key.hashCode() & Integer.MAX_VALUE) % numPartitions;
    }
}
