package me.nalab.gallery.domain

import javax.persistence.Access
import javax.persistence.AccessType
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
@Access(AccessType.FIELD)
class Tendency(
    @Column(name = "tendency_name")
    val name: String,

    @Column(name = "tendency_count")
    val tendencyCount: Int,
)
