package me.nalab.survey.application.port.out.persistence.target.update;

import me.nalab.survey.domain.target.Target;

/**
 * Target의 position 필드를 변경하는 역할을 하는 인터페이스
 *
 * 이 인터페이스의 구현체는 target의 position을 업데이트 합니다
 */
public interface TargetPositionUpdatePort {

	/**
	 * 이 메서드는 target의 position을 업데이트 합니다
	 * @param target 업데이트할 target
	 */
	void updateTargetPosition(Target target);

}
