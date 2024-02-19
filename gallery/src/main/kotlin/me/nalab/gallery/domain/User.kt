package me.nalab.gallery.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class User(
    @Column(name = "user_id", nullable = false, unique = true, updatable = false)
    val id: Long,

    @Column(name = "user_name", nullable = false)
    val name: String,

    @Column(name = "user_nickname", nullable = false)
    val nickname: String,

    @Column(name = "user_image_url", nullable = true)
    val userImageUrl: String,
)
