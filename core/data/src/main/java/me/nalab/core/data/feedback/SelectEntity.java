package me.nalab.core.data.feedback;

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
@Table(name = "select")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectEntity {

	@Id
	@Column
	private Long id;

	@Column
	private Long value;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_feedback_id")
	private ChoiceFormFeedbackEntity choiceFormFeedbackEntity;
}
