package me.nalab.gallery.domain

import me.nalab.core.data.common.TimeBaseEntity
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Version

@Entity
@Table(name = "gallery")
class Gallery(
    @Id
    @Column(name = "id")
    val id: Long,

    @Embedded
    private val user: User,

    @Embedded
    private val survey: Survey,

    @Version
    private val version: Long? = null,
): TimeBaseEntity() {

    fun increaseFeedbackCount() = survey.feedbackCount++

    fun increaseSaveCount() = survey.saveCount++
}
