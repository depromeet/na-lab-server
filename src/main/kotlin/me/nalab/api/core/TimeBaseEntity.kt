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
    protected lateinit var createdAt: Instant

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP(6)", nullable = false)
    protected lateinit var updatedAt: Instant

    @PrePersist
    fun prePersist() {
        val now = TimeUtil.now()
        createdAt = now
        updatedAt = now
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = TimeUtil.now()
    }
}

