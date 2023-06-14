package me.nalab.auth.application.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OAuthAccessTokenResponse {
    private final String token;

    @JsonProperty("token_type")
    private final String tokenType;

    private final String scope;
}
