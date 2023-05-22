package me.nalab.survey.domain.survey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.LongSupplier;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.nalab.survey.domain.exception.IdAlreadyGeneratedException;
import me.nalab.survey.domain.support.IdGeneratable;
import me.nalab.survey.domain.survey.valid.SurveyValidator;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Survey implements IdGeneratable {

	private Long id;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	private final List<FormQuestionable> formQuestionableList;

	Survey(Long id, LocalDateTime createdAt, LocalDateTime updatedAt,
		List<FormQuestionable> formQuestionableList) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.formQuestionableList = sortFormQuestionableList(formQuestionableList);
		SurveyValidator.validSelf(this);
	}

	private List<FormQuestionable> sortFormQuestionableList(List<FormQuestionable> formQuestionableList) {
		return formQuestionableList.stream()
			.sorted()
			.collect(Collectors.toList());
	}

	@Override
	public void withId(LongSupplier idSupplier) {
		if(this.id != null) {
			throw new IdAlreadyGeneratedException(this);
		}
		id = idSupplier.getAsLong();
		for(FormQuestionable formQuestionable : formQuestionableList) {
			formQuestionable.withId(idSupplier);
		}
	}

}
