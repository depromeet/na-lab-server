package me.nalab.api.core

import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object TimeUtil {

    private var clock: Clock? = null

    fun toInstant(): Instant {
        var current = Instant.now()
        if (clock != null) {
            current = Instant.now(clock)
        }
        return formatTo6Digit(current)
    }

    private fun formatTo6Digit(instant: Instant): Instant {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSX")
            .withZone(ZoneId.of("UTC"))
        return Instant.parse(formatter.format(instant))
    }

    fun fixed(clock: Clock?) {
        this.clock = clock
    }

    fun clear() {
        clock = null
    }
}

