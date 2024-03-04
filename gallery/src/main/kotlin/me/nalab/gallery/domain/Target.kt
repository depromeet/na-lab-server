package me.nalab.gallery.domain

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
class Target(
    @Column(name = "target_id", unique = true, nullable = false, updatable = false)
    val targetId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "job", nullable = false, columnDefinition = "TEXT not null")
    val job: Job,
)
