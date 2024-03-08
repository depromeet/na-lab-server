package me.nalab.user.application.common.dto;

import me.nalab.user.domain.user.User;

public record LoginedInfo(
    Long id,
    Long targetId,
    String nickname,
    String email
) {

    public static LoginedInfo from(Long targetId, User user) {
        return new LoginedInfo(user.getId(), targetId, user.getNickname(), user.getEmail());
    }
}
