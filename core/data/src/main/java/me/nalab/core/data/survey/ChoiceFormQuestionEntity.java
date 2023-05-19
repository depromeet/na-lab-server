package me.nalab.core.data.survey;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ChoiceFormQuestionEntity extends FormQuestionEntity {

	@OneToMany(mappedBy = "choiceFormQuestion", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<ChoiceEntity> choiceList;

	@Column(name = "max_selection_count")
	private Integer maxSelectionCount;

	@Enumerated(EnumType.STRING)
	@Column(name = "choice_question_type")
	private ChoiceFormQuestionEntityType choiceFormQuestionType;

	ChoiceFormQuestionEntity(ChoiceFormQuestionEntityBuilder<?, ?> choiceFormQuestionEntityBuilder) {
		super(choiceFormQuestionEntityBuilder);
		this.maxSelectionCount = choiceFormQuestionEntityBuilder.maxSelectionCount;
		this.choiceFormQuestionType = choiceFormQuestionEntityBuilder.choiceFormQuestionType;
		this.choiceList = choiceFormQuestionEntityBuilder.choiceList;
		cascadeChoiceFormQuestion();
	}

	private void cascadeChoiceFormQuestion() {
		for(ChoiceEntity choiceEntity : choiceList) {
			if(choiceEntity.getChoiceFormQuestion() == this) {
				continue;
			}
			choiceEntity.setChoiceFormQuestion(this);
		}
	}

}
