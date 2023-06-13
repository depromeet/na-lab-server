package me.nalab.auth.application.common.dto;

import lombok.Data;

@Data
public class OAuthResourceResponse {
    private final String email;
    private final String nickname;
}
