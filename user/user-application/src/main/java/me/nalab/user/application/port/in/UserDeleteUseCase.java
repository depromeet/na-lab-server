package me.nalab.user.application.port.in;

public interface UserDeleteUseCase {

    /*
        토큰에 해당하는 유저를 삭제합니다.
     */
    void deleteByToken(String token);
}
