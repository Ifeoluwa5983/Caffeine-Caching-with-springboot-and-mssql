package com.caffeine.Caffeine.Caching.exception;

public class NEOSException extends Exception{
    public NEOSException() {
        super();
    }

    public NEOSException(String message) {
        super(message);
    }

    public NEOSException(String message, Throwable cause) {
        super(message, cause);
    }

    public NEOSException(Throwable cause) {
        super(cause);
    }

    protected NEOSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
