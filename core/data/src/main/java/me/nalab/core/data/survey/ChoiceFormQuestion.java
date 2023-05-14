package me.nalab.core.data.survey;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class ChoiceFormQuestion extends FormQuestion {

	@Min(1)
	@Max(20)
	@OneToMany(mappedBy = "choiceFormQuestion", fetch = FetchType.LAZY)
	private List<Choice> choiceList;

	@Min(1)
	@Max(20)
	@Column(name = "max_selection_count")
	private Integer maxSelectionCount;

	@Enumerated(EnumType.STRING)
	@Column(name = "choice_question_type")
	private ChoiceFormQuestionType choiceFormQuestionType;

}
