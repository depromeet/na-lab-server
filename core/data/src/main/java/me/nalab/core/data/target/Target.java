package me.nalab.core.data.target;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.nalab.core.data.common.TimeBaseEntity;
import me.nalab.core.data.survey.Survey;

@Entity
@Table(name = "target")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class Target extends TimeBaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "target_id")
	private Long targetId;

	@Column(name = "application_id", nullable = false, unique = true, updatable = false)
	private Long applicationId;

	@Column(name = "target_name", nullable = false)
	private String nickname;


	@OneToMany(mappedBy = "target",
		cascade = CascadeType.ALL,
		orphanRemoval = true,
		fetch = FetchType.LAZY)
	private List<Survey> surveyList;

}
