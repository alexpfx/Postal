package br.com.alexandrealessi.postal.exceptions;

/**
 * Created by alexandre on 14/04/15.
 */
public class PostalException extends RuntimeException {
    public PostalException() {
    }

    public PostalException(String detailMessage) {
        super(detailMessage);
    }

    public PostalException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public PostalException(Throwable throwable) {
        super(throwable);
    }
}
