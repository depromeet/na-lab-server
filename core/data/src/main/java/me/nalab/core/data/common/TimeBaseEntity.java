package me.nalab.core.data.common;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class TimeBaseEntity {

	@CreatedDate
	@Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
	protected LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_at", columnDefinition = "TIMESTAMP", nullable = false)
	protected LocalDateTime updatedAt;

}
