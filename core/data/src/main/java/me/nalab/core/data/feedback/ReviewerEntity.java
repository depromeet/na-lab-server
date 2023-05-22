package me.nalab.core.data.feedback;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.nalab.core.data.common.TimeBaseEntity;

@Entity
@Table(name = "reviewer")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewerEntity extends TimeBaseEntity {

	@Id
	@Column(name = "reviewer_id")
	private Long id;

	@Column(name = "collaboration_experience", nullable = false)
	private Boolean collaborationExperience;

	@Column(name = "position", nullable = false)
	private String position;

	@Column(name = "nickname", nullable = false)
	private String nickname;
}
