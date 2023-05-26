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
	private final Provider provider;
	private final String token;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
