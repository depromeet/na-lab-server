package me.nalab.gallery.domain

import javax.persistence.*

@Embeddable
class Survey(
    @Column(name = "survey_id", nullable = false, unique = true, updatable = false)
    val id: Long,

    @Column(name = "feedback_count", nullable = false)
    var feedbackCount: Int = 0,

    @Column(name = "save_count", nullable = false)
    var saveCount: Int = 0,

    @Embedded
    var feedback: Feedback,

    @ElementCollection
    @CollectionTable(
        name = "gallery_survey_tendency",
        joinColumns = [JoinColumn(name = "id")],
    )
    val tendencies: MutableList<Tendency>,
)
