package com.ebay.ebayclientapi.exception;

public class InvalidException extends RuntimeException {
    public InvalidException() {
        super();
    }

    public InvalidException(String message, Throwable clause) {
        super(message, clause);
    }

    public InvalidException(String message) {
        super(message);
    }

    public InvalidException(Throwable clause) {
        super(clause);
    }
}
