package me.nalab.auth.interceptor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrorTemplate {

    @JsonProperty("response_messages")
    private final String message;

    private ErrorTemplate(String message) {
        this.message = message;
    }

    public static ErrorTemplate of(String message) {
        return new ErrorTemplate(message);
    }

}

