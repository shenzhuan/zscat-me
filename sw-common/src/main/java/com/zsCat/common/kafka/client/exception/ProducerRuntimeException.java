package com.zsCat.common.kafka.client.exception;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-23
 */
public class ProducerRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 5222623760553747260L;

    public ProducerRuntimeException() {
        super();
    }

    public ProducerRuntimeException(String message) {
        super(message);
    }

    public ProducerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProducerRuntimeException(Throwable cause) {
        super(cause);
    }
}
