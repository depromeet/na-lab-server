package me.nalab.user.application.port.out.persistence;

public interface UserDeletePort {

    /*
        user id에 해당하는 유저를 삭제합니다.
     */
    void deleteUserById(Long userId);
}
