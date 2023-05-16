package me.nalab.survey.application.port.out.persistence;

/**
 * 저장되어있는 Target을 조회하는 인터페이스
 */
public interface FindTargetPort {

	/**
	 * targetId에 해당하는 target이 저장되어있는지 확인합니다.
	 *
	 * @param targetId 존재하는지 확인할 target의 ID
	 * @return boolean 존재한다면 true / 존재하지 않는다면, false
	 */
	boolean isExistTarget(Long targetId);

}
