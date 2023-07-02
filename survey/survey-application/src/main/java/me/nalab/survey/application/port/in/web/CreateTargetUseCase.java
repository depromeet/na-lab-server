package me.nalab.survey.application.port.in.web;

import me.nalab.survey.application.common.target.dto.CreateTargetRequest;

// 주석 작성 필요
public interface CreateTargetUseCase {
    long create(CreateTargetRequest request);
}
