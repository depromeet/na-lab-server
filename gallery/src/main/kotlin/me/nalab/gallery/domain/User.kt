package me.nalab.gallery.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class User(
    @Column(name = "user_id", nullable = false, unique = true, updatable = false)
    internal val id: Long,

    @Column(name = "user_name", nullable = false)
    internal val name: String,

    @Column(name = "user_position", nullable = false)
    internal val position: String,
)
