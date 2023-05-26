package me.nalab.user.domain.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
public class User {
	private final long id;
	private Provider provider;
	private String token;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
}
