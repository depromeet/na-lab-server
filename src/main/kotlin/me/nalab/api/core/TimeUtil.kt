package me.nalab.api.core

import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object TimeUtil {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSX")
        .withZone(ZoneId.of("UTC"))

    private var clock: Clock? = null

    fun now(): Instant {
        val current = if (clock != null) {
            Instant.now(clock)
        } else {
            Instant.now()
        }
        return formatTo6Digit(current)
    }

    private fun formatTo6Digit(instant: Instant): Instant = Instant.parse(formatter.format(instant))

    fun fixed(clock: Clock) {
        this.clock = clock
    }

    fun clear() {
        clock = null
    }
}

