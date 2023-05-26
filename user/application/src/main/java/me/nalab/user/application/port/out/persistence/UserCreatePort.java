package me.nalab.user.application.port.out.persistence;

import me.nalab.user.domain.user.User;

/**
 * 새로운 User Domain을 영속화합니다.
 */
public interface UserCreatePort {

	/**
	 * user domain 객체를 영속화
	 * @param user 저장할 Survey의 정보
	 */
	void createUser(User user);

}
