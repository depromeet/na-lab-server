package me.nalab.gallery.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Survey(
    @Column(name = "survey_id", nullable = false, unique = true, updatable = false)
    internal val id: Long,

    @Column(name = "feedback_count", nullable = false)
    internal var feedbackCount: Int = 0,

    @Column(name = "save_count", nullable = false,)
    internal var saveCount: Int = 0,
){
}
