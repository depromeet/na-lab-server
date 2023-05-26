package me.nalab.core.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * 하나의 요청안에서 일관된 시간을 반환하는 유틸 입니다.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TimeUtil {

	private final Instant instant;

	public TimeUtil() {
		instant = Instant.now();
	}

	/**
	 * 요청 시간을 LocalDateTime으로 반환합니다.
	 * @return LocalDateTime 클라이언트의 요청이 들어온 시간
	 */
	public LocalDateTime toLocalDateTime() {
		return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
	}

	/**
	 * 요청 시간을 Instant로 반환합니다.
	 * @return Instant 클라이언트의 요청이 들어온 시간
	 */
	public Instant toInstant() {
		return instant;
	}

}
