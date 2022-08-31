package com.mallplus.common.exception;

/**
 * 幂等性异常
 *
 * @author mall
 */
public class IdempotencyException extends RuntimeException {
    private static final long serialVersionUID = 6610083281801529147L;

    public IdempotencyException(String message) {
        super(message);
    }
}
