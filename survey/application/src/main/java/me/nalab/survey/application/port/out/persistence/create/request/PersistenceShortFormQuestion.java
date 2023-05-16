package me.nalab.survey.application.port.out.persistence.create.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PersistenceShortFormQuestion extends PersistenceFormQuestionable {

	private final PersistenceShortFormQuestionType persistenceShortFormQuestionType;

}
