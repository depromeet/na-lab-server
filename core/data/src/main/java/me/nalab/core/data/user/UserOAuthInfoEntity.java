package me.nalab.core.data.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.nalab.core.data.common.TimeBaseEntity;

@Table(name = "user_oauth_info)")
@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserOAuthInfoEntity extends TimeBaseEntity {
	@Id
	private Long id;

	@Column(nullable = false, updatable = false)
	private String provider;

	@Column(nullable = false)
	private String email;

	@Column(nullable = true)
	private String username;

	@Column(nullable = true)
	private String phoneNumber;

	@ManyToOne
	private UserEntity userEntity;
}

