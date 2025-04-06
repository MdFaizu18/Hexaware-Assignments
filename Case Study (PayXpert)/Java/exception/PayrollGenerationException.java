package com.payxpert.exception;


public class PayrollGenerationException extends Exception {
    public PayrollGenerationException(String message) {
        super(message);
    }

    public PayrollGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
