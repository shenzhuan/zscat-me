package com.zsCat.common.kafka.client.exception;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-23
 */
public class ConsumerRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 5122623760553747260L;

    public ConsumerRuntimeException() {
        super();
    }

    public ConsumerRuntimeException(String message) {
        super(message);
    }

    public ConsumerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsumerRuntimeException(Throwable cause) {
        super(cause);
    }
}
