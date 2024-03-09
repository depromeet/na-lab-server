package me.nalab.api.core

import java.time.Instant
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate


@MappedSuperclass
abstract class TimeBaseEntity {
    @Column(
        name = "created_at",
        columnDefinition = "TIMESTAMP(6)",
        nullable = false,
        updatable = false,
    )
    protected var createdAt: Instant? = null

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP(6)", nullable = false)
    protected var updatedAt: Instant? = null

    @PrePersist
    fun prePersist() {
        val now = Instant.now()
        createdAt = if (createdAt != null) createdAt else now
        updatedAt = if (updatedAt != null) updatedAt else now
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = if (updatedAt != null) updatedAt else Instant.now()
    }
}

