package me.nalab.user.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
public class User {
	private final long id;
	private final String nickname;
	private final String email;
	private final Instant createdAt;
	private Instant updatedAt;
}
