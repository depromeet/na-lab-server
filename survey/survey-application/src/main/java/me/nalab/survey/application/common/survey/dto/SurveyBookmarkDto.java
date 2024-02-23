package me.nalab.survey.application.common.survey.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
public class SurveyBookmarkDto {

    private final Long targetId;
    private final Long surveyId;
    private final String nickname;
    private final String position;
    private final String job;
    private final String imageUrl;

}
