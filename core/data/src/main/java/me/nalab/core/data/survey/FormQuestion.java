package me.nalab.core.data.survey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.nalab.core.data.common.TimeStamp;

@Entity
@Table(name = "form_question")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class FormQuestion extends TimeStamp {

	@Id
	@GeneratedValue
	@Column(name = "form_question_id")
	protected Long formQuestionId;

	@Column(name = "application_id", nullable = false, unique = true, updatable = false)
	protected Long applicationId;

	@Size(min = 1, max = 45)
	@Column(name = "title", nullable = false)
	protected String title;

	@Column(name = "order", nullable = false)
	@Max(25)
	@Min(1)
	protected Integer order;

	@Enumerated(EnumType.STRING)
	protected QuestionType questionType;

	@ManyToOne
	@JoinColumn(name = "survey_id", nullable = false)
	protected Survey survey;

}
