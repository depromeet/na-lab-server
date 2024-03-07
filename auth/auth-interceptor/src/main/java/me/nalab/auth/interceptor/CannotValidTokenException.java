package me.nalab.auth.interceptor;

public class CannotValidTokenException extends RuntimeException {

    public CannotValidTokenException(String message) {
        super(message);
    }
}
