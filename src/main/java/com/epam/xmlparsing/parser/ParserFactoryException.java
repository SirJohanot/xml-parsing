package com.epam.xmlparsing.parser;

public class ParserFactoryException extends Exception {

    public ParserFactoryException() {
    }

    public ParserFactoryException(String message) {
        super(message);
    }

    public ParserFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserFactoryException(Throwable cause) {
        super(cause);
    }

    public ParserFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
