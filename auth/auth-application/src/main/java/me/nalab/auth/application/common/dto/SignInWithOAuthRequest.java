package me.nalab.auth.application.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInWithOAuthRequest {

    private final String providerName;
    private final String email;
    private final String username;
    private final String phoneNumber;

}
