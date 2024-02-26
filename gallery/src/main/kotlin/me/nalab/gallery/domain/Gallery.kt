package me.nalab.gallery.domain

import me.nalab.core.data.common.TimeBaseEntity
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "gallery")
class Gallery(
    @Id
    @Column(name = "gallery_id")
    val id: Long,

    @Embedded
    val target: Target,

    @Embedded
    val survey: Survey,

    @Column(name = "update_order", columnDefinition = "TIMESTAMP(6)", nullable = false)
    private var updateOrder: Instant,

    @Version
    private var version: Long? = null,
) : TimeBaseEntity() {

    constructor(
        id: Long,
        targetId: Long,
        job: Job,
        surveyId: Long,
        bookmarkedCount: Int,
        updateOrder: Instant,
    ): this(id, Target(targetId, job), Survey(surveyId, bookmarkedCount), updateOrder)

    fun getTargetId(): Long = target.targetId

    fun getJob(): Job = target.job

    fun getBookmarkedCount(): Int = survey.bookmarkedCount

    fun increaseBookmarkedCount() {
        survey.bookmarkedCount++
    }
}
