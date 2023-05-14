package me.nalab.core.data.common;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class TimeStamp {

	@Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
	protected LocalDateTime createdAt;

	@Column(name = "updated_at", columnDefinition = "TIMESTAMP", nullable = false)
	protected LocalDateTime updatedAt;

}
