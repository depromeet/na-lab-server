package me.nalab.survey.application.port.in.web.target.find;

import me.nalab.survey.application.common.dto.TargetDto;

/**
 * Target을 조회하는 Usecase
 * 이 인터페이스를 이용해서 Target을 조회할 수 있음
 */
public interface TargetFindUseCase {

	/**
	 * targetId에 해당하는 target을
	 * TargetDto형식으로 변환한 후 가져옴
	 */
	TargetDto findTarget(Long targetId);
}
