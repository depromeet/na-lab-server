package me.nalab.api.survey.domain.survey.value

import javax.persistence.Column
import javax.persistence.Embeddable

@JvmInline
value class Position(
    val value: String,
) {

    companion object {
        fun empty(): Position = Position("")
    }
}
