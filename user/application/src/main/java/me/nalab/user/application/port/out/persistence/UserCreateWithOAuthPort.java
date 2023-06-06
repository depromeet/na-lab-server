package me.nalab.user.application.port.out.persistence;

import me.nalab.user.domain.user.User;
import me.nalab.user.domain.user.UserOAuthInfo;

/**
 * 새로운 User Domain과 UserOAuthInfo Domain을 영속화합니다.
 */
public interface UserCreateWithOAuthPort {

    /**
     * user domain과 userOAuthInfo domain 객체를 영속화
     * @param user 저장할 유저의 정보
     * @param userOAuthInfo 저장할 유저의 OAuth 정보
     */
    void createUserWithOAuth(User user, UserOAuthInfo userOAuthInfo);

}
