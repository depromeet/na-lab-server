package me.nalab.oauth.application.kakao.dto;

import lombok.Data;

@Data
public class ResourceResponse {
    private final String id;
    private final KakaoAccount kakaoAccount;
}
