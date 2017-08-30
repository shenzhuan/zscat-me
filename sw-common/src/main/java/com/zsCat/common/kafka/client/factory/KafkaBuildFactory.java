package com.zsCat.common.kafka.client.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-28
 */
public class KafkaBuildFactory {

    protected static final String PRODUCER_CONFIG = "producer.properties";

    protected static final String CONSUMER_CONFIG = "consumer.properties";

    protected static Properties init0(final String propertiesName) throws IOException {

        InputStream inputStream;
        ClassLoader cl = KafkaBuildFactory.class.getClassLoader();
        if (cl != null) {
            inputStream = cl.getResourceAsStream(propertiesName);
        } else {
            inputStream = ClassLoader.getSystemResourceAsStream(propertiesName);
        }
        Properties dbProps =  new Properties();
        try {
            dbProps.load(inputStream);
        } finally {
            inputStream.close();
        }
        return dbProps;
    }
}
