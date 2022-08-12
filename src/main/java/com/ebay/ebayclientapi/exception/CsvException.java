package com.ebay.ebayclientapi.exception;

public class CsvException extends RuntimeException {
    
    public CsvException() {
        super();
    }

    public CsvException(String message, Throwable clause) {
        super(message, clause);
    }

    public CsvException(String message) {
        super(message);
    }

    public CsvException(Throwable clause) {
        super(clause);
    }
}