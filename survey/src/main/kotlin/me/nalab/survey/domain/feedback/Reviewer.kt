package me.nalab.survey.domain.feedback

import me.nalab.core.data.common.TimeBaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "reviewer")
@Table(name = "reviewer")
class Reviewer(
    @Id
    @Column(name = "reviewer_id")
    val id: Long,

    @Column(name = "nick_name", nullable = false)
    var nickName: String,

    @Column(name = "collaboration_experience", nullable = false)
    val collaborationExperience: Boolean,

    @Column(name = "position", nullable = false)
    var position: String? = null,
) : TimeBaseEntity()
