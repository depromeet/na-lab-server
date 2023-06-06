package me.nalab.user.application.port.in;

import me.nalab.user.application.common.dto.CreateUserWithOAuthRequest;

/**
 * OAuth 정보를 이용하여 User와 UserOAuthInfo를 생성합니다.
 */
public interface UserCreateWithOAuthUseCase {

    /**
     * OAuth 정보를 이용해서 User와 UserOAuthInfo를 생성합니다.
     * @param request OAuth 정보를 포함하고 있는 dto
     */
    void createUser(CreateUserWithOAuthRequest request);
}
