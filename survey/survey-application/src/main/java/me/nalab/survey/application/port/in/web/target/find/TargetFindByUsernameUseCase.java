package me.nalab.survey.application.port.in.web.target.find;

import me.nalab.survey.application.common.survey.dto.TargetDto;

import java.util.Optional;

/**
 *  유저의 이름을 통해서 target을 조회합니다.
 */
public interface TargetFindByUsernameUseCase {

    /**
     * 유저 이름을 통해 target을 조회합니다.
     * 여러 개가 있을 경우 가장 마지막으로 생성된 타겟을 조회합니다.
     *
     * @param username 사용자의 이름
     * @return target 데이터를 가진 dto
     */
    Optional<TargetDto> findTargetByUsername(String username);

}
