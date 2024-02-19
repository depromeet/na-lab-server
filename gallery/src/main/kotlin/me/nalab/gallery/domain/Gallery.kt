package me.nalab.gallery.domain

import me.nalab.core.data.common.TimeBaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "gallery")
class Gallery(
    @Id
    @Column(name = "gallery_id")
    val id: Long,

    @Column(name = "target_id", unique = true, nullable = false, updatable = false)
    val targetId: Long,

    @Column(name = "survey_id", unique = true, nullable = false)
    val surveyId: Long,
) : TimeBaseEntity()
