package me.nalab.survey.jpa.adaptor.create.repository;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.out.persistence.CreateTargetPort;
import me.nalab.survey.domain.target.Target;
import me.nalab.survey.jpa.adaptor.common.mapper.TargetEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreateTargetAdaptor implements CreateTargetPort {

    private final TargetCreateJpaRepository targetCreateJpaRepository;

    // 테스트 코드 작성 필요
    @Override
    public long create(Target target) {
        var entity = TargetEntityMapper.toTargetEntity(target);
        var savedEntity = targetCreateJpaRepository.save(entity);

        return savedEntity.getId();
    }
}
