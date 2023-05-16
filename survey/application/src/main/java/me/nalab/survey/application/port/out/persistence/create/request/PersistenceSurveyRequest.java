package me.nalab.survey.application.port.out.persistence.create.request;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class PersistenceSurveyRequest {

	private final Long id;
	private final Long targetId;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	private final List<PersistenceFormQuestionable> persistenceFormQuestionableList;

}
