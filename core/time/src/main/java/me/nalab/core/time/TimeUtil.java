package me.nalab.core.time;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * 저장된 시간을 일간되게 반환하는 util 입니다.
 */
public interface TimeUtil {

	/**
	 * 저장된 시간을 LocalDateTime으로 반환합니다.
	 * @return LocalDateTime
	 */
	LocalDateTime toLocalDateTime();

	/**
	 * 저장된 시간을 Instant로 반환합니다.
	 * @return Instant
	 */
	Instant toInstant();

}
