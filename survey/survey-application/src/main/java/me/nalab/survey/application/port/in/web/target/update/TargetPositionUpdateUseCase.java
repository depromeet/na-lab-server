package me.nalab.survey.application.port.in.web.target.update;

/**
 *
 * targetId에 해당하는 타겟의 position을 수정합니다.
 */
public interface TargetPositionUpdateUseCase {

	/**
	 *
	 * @param targetId 해당 타겟의 id
	 * @param position 변경할 position
	 */
	void updateTargetPosition(Long targetId, String position);

}
