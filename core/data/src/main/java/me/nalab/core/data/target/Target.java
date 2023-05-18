package me.nalab.core.data.target;

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
@Table(name = "target")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Target extends TimeBaseEntity {

	@Id
	@Column(name = "target_id")
	private Long id;

	@Column(name = "target_name", nullable = false)
	private String nickname;

}
