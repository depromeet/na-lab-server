package me.nalab.auth.application.common.dto;

import lombok.Data;

@Data
public class OAuthAccessTokenRequest {
    private final String providerName;
    private final String authToken;
}
