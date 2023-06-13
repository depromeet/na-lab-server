package me.nalab.auth.web.adaptor.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserOAuthRejectException extends RuntimeException {

    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
