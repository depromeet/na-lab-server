package me.nalab.core.time;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

	private static Clock clock = null;

	private TimeUtil() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"TimeUtil()\"");
	}

	public static Instant toInstant() {
		var current = Instant.now();
		if (clock != null) {
			current = Instant.now(clock);
		}
		return formatTo6Digit(current);
	}

	private static Instant formatTo6Digit(Instant instant) {
		var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSX")
			.withZone(ZoneId.of("UTC"));
		return Instant.parse(formatter.format(instant));
	}

	public static void fixed(Clock clock) {
		TimeUtil.clock = clock;
	}

	public static void clear() {
		TimeUtil.clock = null;
	}
}
