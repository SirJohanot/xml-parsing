package com.patiun.xmlparsing.parser;

public class DirectorException extends Exception {

    public DirectorException() {
    }

    public DirectorException(String message) {
        super(message);
    }

    public DirectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DirectorException(Throwable cause) {
        super(cause);
    }

    public DirectorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
