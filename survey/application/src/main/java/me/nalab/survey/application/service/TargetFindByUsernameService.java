package me.nalab.survey.application.service;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.survey.dto.TargetDto;
import me.nalab.survey.application.common.survey.mapper.TargetDtoMapper;
import me.nalab.survey.application.port.in.web.target.find.TargetFindByUsernameUseCase;
import me.nalab.survey.application.port.out.persistence.target.find.TargetFindPort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TargetFindByUsernameService implements TargetFindByUsernameUseCase {

    private final TargetFindPort targetFindPort;

    @Override
    public Optional<TargetDto> findTargetByUsername(String username) {
        Assert.hasText(username, "타겟 조회 시 username은 필수 입니다.");

        var allTargets = targetFindPort.findAllByUsername(username);
        if (allTargets.isEmpty()) {
            return Optional.empty();
        }

        var lastIndex = allTargets.size() - 1;
        var latestTarget = allTargets.get(lastIndex);
        var latestTargetDto = TargetDtoMapper.toTargetDto(latestTarget);
        return Optional.of(latestTargetDto);
    }
}
