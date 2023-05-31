package me.nalab.core.data.survey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.nalab.core.data.common.TimeBaseEntity;

@Entity
@Table(name = "form_question")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class FormQuestionEntity extends TimeBaseEntity {

	@Id
	@Column(name = "form_question_id")
	protected Long id;

	@Column(name = "title", nullable = false, length = 45)
	protected String title;

	@Column(name = "orders", nullable = false)
	protected Integer order;

	@Enumerated(EnumType.STRING)
	@Column(name = "question_type")
	protected QuestionEntityType questionType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_id", nullable = false)
	protected SurveyEntity survey;

}
