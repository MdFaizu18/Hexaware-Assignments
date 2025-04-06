package com.payxpert.exception;


public class TaxCalculationException extends Exception {
    public TaxCalculationException(String message) {
        super(message);
    }

    public TaxCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
