package me.nalab.core.data.feedback;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.nalab.core.data.common.TimeBaseEntity;

@Entity
@Table(name = "feedback")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackEntity extends TimeBaseEntity {

	@Id
	@Column(name = "feedback_id")
	private Long id;

	@JoinColumn(name = "survey_id", nullable = false)
	private Long surveyId;

	@JoinColumn(name = "is_read", nullable = false)
	private Boolean isRead;

	@OneToOne
	@JoinColumn(name = "reviewer_id")
	private ReviewerEntity reviewer;

}
