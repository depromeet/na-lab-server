package me.nalab.gallery.domain

import javax.persistence.Access
import javax.persistence.AccessType
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
@Access(AccessType.FIELD)
class Feedback(
    @Column(name = "feedback_reply")
    val reply: String,
)
