package me.nalab.core.data.survey;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
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

	@OneToMany(mappedBy = "choiceFormQuestion",
				cascade = CascadeType.ALL,
				orphanRemoval = true,
				fetch = FetchType.LAZY)
	@Max(20)
	@Min(1)
	private List<Choice> choiceList;

	@Column(name = "max_selection_count")
	@Max(20)
	@Min(1)
	private Integer maxSelectionCount;

	@Enumerated(EnumType.STRING)
	private ChoiceFormQuestionType choiceFormQuestionType;

}
