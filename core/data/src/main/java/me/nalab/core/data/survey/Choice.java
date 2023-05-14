package me.nalab.core.data.survey;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Valid
public class Choice {

	@Id
	@GeneratedValue
	@Column(name = "choice_id")
	private Long id;

	@Column(name = "application_id", nullable = false, unique = true, updatable = false)
	private Long applicationId;

	@Size(min = 1, max = 18)
	@Column(name = "content", length = 18, nullable = false)
	private String content;

	@Min(1)
	@Max(20)
	@Column(name = "order", nullable = false)
	private Integer order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_question_id", nullable = false)
	private ChoiceFormQuestion choiceFormQuestion;

}
