package me.nalab.survey.application.service.create;

import lombok.RequiredArgsConstructor;
import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.application.common.target.dto.CreateTargetRequest;
import me.nalab.survey.application.port.in.web.CreateTargetUseCase;
import me.nalab.survey.application.port.out.persistence.CreateTargetPort;
import me.nalab.survey.domain.target.Target;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CreateTargetService implements CreateTargetUseCase {

    private final IdGenerator idGenerator;
    private final CreateTargetPort createTargetPort;

    // 테스트 코드 작성 필요
    @Override
    public long create(CreateTargetRequest request) {
        var target = Target.builder()
                .nickname(request.getUsername())
                .surveyList(Collections.emptyList())
                .build();
        target.withId(idGenerator::generate);

        createTargetPort.create(target);

        return target.getId();
    }
}
