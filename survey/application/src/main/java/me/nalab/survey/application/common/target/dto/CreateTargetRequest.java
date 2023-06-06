package me.nalab.survey.application.common.target.dto;

import lombok.Data;
import me.nalab.survey.domain.survey.Survey;

@Data
public final class CreateTargetRequest {
    private final String username;
}
