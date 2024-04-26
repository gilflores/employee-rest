package org.directory.exception;

public class NotFountException extends Exception {
    private static final long serialVersionUID = 4195767788022286501L;

    public NotFountException() {
        super();
    }

    public NotFountException(String message) {
        super(message);
    }

    public NotFountException(Throwable cause) {
        super(cause);
    }

    public NotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFountException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

