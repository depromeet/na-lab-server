package me.nalab.survey.application.port.out.persistence;

import me.nalab.survey.domain.target.Target;

// 주석 작성하기
public interface CreateTargetPort {
    long create(Target target);
}
