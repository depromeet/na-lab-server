package me.nalab.user.domain.user;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(of = {"id"})
public class User {
	private final long id;
	private Provider provider;
	private String token;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	User(long id, Provider provider, String token, LocalDateTime createdAt) {
		this.id = id;
		this.provider = provider;
		this.token = token;
		this.createdAt = createdAt;
	}
}
