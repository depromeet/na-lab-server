package me.nalab.auth.application.common.dto;

import lombok.Data;

@Data
public class AccessToken {
    private final String tokenType;
    private final String token;
}
