package me.nalab.oauth.application.kakao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoAccount {
    private final boolean isEmailValid;
    private final boolean isEmailVerified;
    private final String email;
    private final String name;
}
