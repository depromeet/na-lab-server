package me.nalab.auth.application.common.dto;

import lombok.Data;
import me.nalab.user.domain.user.Provider;

@Data
public class OAuthAccessTokenRequest {
    private final Provider provider;
    private final String authToken;
}
