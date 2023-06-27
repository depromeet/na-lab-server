package me.nalab.auth.application.common.dto;

import lombok.Data;

@Data
public class OAuthResourceRequest {
    private final String providerName;
    private final String token;
    private final String tokenType;
}
