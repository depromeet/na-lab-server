package me.nalab.api.survey.domain.survey.value

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Position(
    @Column(name = "position", nullable = false)
    val value: String,
) {

    companion object {
        fun empty(): Position = Position("")
    }
}
