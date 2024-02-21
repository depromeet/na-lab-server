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
) : TimeBaseEntity()
