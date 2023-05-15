package me.nalab.survey.domain.survey;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.domain.survey.valid.SurveyValidator;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Survey {

	private final Long id;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	private final List<FormQuestionable> formQuestionableList;

	Survey(Long id, LocalDateTime createdAt, LocalDateTime updatedAt,
		List<FormQuestionable> formQuestionableList) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.formQuestionableList = formQuestionableList;
		SurveyValidator.validSelf(this);
	}

	public Survey createDefaultFormQuestions(IdGenerator idGenerator) {
		return builder()
			.id(id)
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.formQuestionableList(getDefaultFormQuestionableList(idGenerator))
			.build();
	}

	private List<FormQuestionable> getDefaultFormQuestionableList(IdGenerator idGenerator) {
		List<FormQuestionable> mergedFormQuestionList = new ArrayList<>();
		mergedFormQuestionList.addAll(ChoiceFormQuestion.getDefaultChoiceFormQuestion(idGenerator));
		mergedFormQuestionList.addAll(ShortFormQuestion.getDefaultShortFormQuestion(idGenerator));
		List<FormQuestionable> ordinaryFormQuestionableList = formQuestionableList.stream()
			.map(fq -> fq.ofIncreaseOrder(mergedFormQuestionList.size()))
			.collect(Collectors.toList());
		mergedFormQuestionList.addAll(ordinaryFormQuestionableList);
		return mergedFormQuestionList;
	}

}
