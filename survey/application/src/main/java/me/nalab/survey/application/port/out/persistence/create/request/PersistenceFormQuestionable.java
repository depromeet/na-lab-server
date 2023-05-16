package me.nalab.survey.application.port.out.persistence.create.request;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public abstract class PersistenceFormQuestionable {

	protected final Long id;
	protected final String title;
	protected final LocalDateTime createdAt;
	protected final LocalDateTime updatedAt;
	protected final Integer order;
	protected final PersistenceQuestionType persistenceQuestionType;

}
