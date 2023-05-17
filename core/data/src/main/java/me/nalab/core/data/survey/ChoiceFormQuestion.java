package me.nalab.core.data.survey;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceFormQuestion extends FormQuestion {

	@OneToMany(mappedBy = "choiceFormQuestion", fetch = FetchType.LAZY)
	private List<Choice> choiceList;

	@Column(name = "max_selection_count")
	private Integer maxSelectionCount;

	@Enumerated(EnumType.STRING)
	@Column(name = "choice_question_type")
	private ChoiceFormQuestionType choiceFormQuestionType;

}
