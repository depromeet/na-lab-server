package me.nalab.auth.application.common.dto;

import lombok.Data;

@Data
public class SignUpWithOAuthRequest {

    private final String providerName;
    private final String email;
    private final String username;
    private final String phoneNumber;

}
