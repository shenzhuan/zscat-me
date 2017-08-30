package com.zsCat.common.kafka.client.exception;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-22
 */
public class ProducerConfigException extends RuntimeException {

    private static final long serialVersionUID = 5922623760553747260L;

    public ProducerConfigException() {
        super();
    }

    public ProducerConfigException(String message) {
        super(message);
    }

    public ProducerConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProducerConfigException(Throwable cause) {
        super(cause);
    }
}
