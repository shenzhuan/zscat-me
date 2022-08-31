package com.mallplus.common.exception;

/**
 * 业务异常
 *
 * @author mall
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 6610083281801529147L;

    public BusinessException(String message) {
        super(message);
    }
}
