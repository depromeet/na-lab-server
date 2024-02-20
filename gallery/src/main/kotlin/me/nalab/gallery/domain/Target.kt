package me.nalab.gallery.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Target(
    @Column(name = "target_id", unique = true, nullable = false, updatable = false)
    val targetId: Long,

    @Column(name = "position", nullable = false)
    val position: String,
) {
}
