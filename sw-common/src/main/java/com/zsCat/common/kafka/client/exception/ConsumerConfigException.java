package com.zsCat.common.kafka.client.exception;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-22
 */
public class ConsumerConfigException extends RuntimeException {

    private static final long serialVersionUID = 5822623760553747260L;

    public ConsumerConfigException() {
        super();
    }

    public ConsumerConfigException(String message) {
        super(message);
    }

    public ConsumerConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsumerConfigException(Throwable cause) {
        super(cause);
    }
}
