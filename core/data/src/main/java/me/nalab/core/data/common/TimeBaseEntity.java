package me.nalab.core.data.common;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class TimeBaseEntity {

	@Column(name = "created_at", columnDefinition = "TIMESTAMP(6)", nullable = false, updatable = false)
	protected LocalDateTime createdAt;

	@Column(name = "updated_at", columnDefinition = "TIMESTAMP(6)", nullable = false)
	protected LocalDateTime updatedAt;

	@PrePersist
 	void prePersist() {
		var now = LocalDateTime.now();

		createdAt = createdAt != null ? createdAt : now;
		updatedAt = updatedAt != null ? updatedAt : now;
	}

	@PreUpdate
	void preUpdate() {
		updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
	}

}
