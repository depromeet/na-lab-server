package me.nalab.core.data.survey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "choice")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceEntity {

	@Id
	@Column(name = "choice_id")
	private Long id;

	@Column(name = "content", length = 18, nullable = false)
	private String content;

	@Column(name = "order", nullable = false)
	private Integer order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_question_id", nullable = false)
	private ChoiceFormQuestionEntity choiceFormQuestion;

}
