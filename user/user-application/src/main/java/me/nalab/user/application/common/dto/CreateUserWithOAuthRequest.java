package me.nalab.user.application.common.dto;

import lombok.Data;
import me.nalab.user.domain.user.Provider;

@Data
public class CreateUserWithOAuthRequest {

    private final Provider provider;
    private final String email;
    private final String username;
    private final String phoneNumber;

}
