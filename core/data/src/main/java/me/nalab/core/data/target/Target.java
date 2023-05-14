package me.nalab.core.data.target;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@Column(name = "target_id")
	private Long id;

	@Column(name = "target_name", nullable = false)
	private String nickname;


	@OneToMany(mappedBy = "target", fetch = FetchType.LAZY)
	private List<Survey> surveyList;

}
