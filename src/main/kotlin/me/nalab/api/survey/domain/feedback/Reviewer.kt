package me.nalab.api.survey.domain.feedback

import me.nalab.api.core.TimeBaseEntity
import me.nalab.api.survey.domain.feedback.value.NickName
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
    internal var nickName: NickName,

    @Column(name = "collaboration_experience", nullable = false)
    val collaborationExperience: Boolean,

    @Column(name = "position", nullable = false)
    var position: String,
) : TimeBaseEntity()
