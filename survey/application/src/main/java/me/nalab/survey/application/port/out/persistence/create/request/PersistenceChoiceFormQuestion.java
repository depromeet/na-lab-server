package me.nalab.survey.application.port.out.persistence.create.request;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PersistenceChoiceFormQuestion extends PersistenceFormQuestionable {

	private final List<PersistenceChoice> persistenceChoiceList;
	private final Integer maxSelectionCount;
	private final PersistenceChoiceFormQuestionType persistenceChoiceFormQuestionType;

}
