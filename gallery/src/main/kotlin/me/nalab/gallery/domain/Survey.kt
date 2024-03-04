package me.nalab.gallery.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Survey(
    @Column(name = "survey_id", nullable = false, unique = true, updatable = false)
    val id: Long,

    @Column(name = "bookmarked_count", nullable = false)
    var bookmarkedCount: Int = 0,
)
