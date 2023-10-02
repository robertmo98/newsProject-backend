package edu.robertmo.newsproject.error;

public class NewsException extends RuntimeException{
    public NewsException(String message) {
        super(message);
    }

    public NewsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewsException(Throwable cause) {
        super(cause);
    }

    public NewsException(String message, Throwable cause, boolean enableSuppression, boolean writeStackTrace) {
        super(message, cause, enableSuppression, writeStackTrace);
    }
}
