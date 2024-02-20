package me.nalab.gallery.domain

import me.nalab.core.data.common.TimeBaseEntity
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

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
) : TimeBaseEntity()
