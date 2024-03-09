package me.nalab.api.survey.domain.feedback.value

import javax.persistence.Embeddable

@JvmInline
@Embeddable
value class NickName(
    val value: String
) {

    companion object {
        private const val FIRST_NAME = "A"
        fun firstNickName(): NickName =
            NickName(FIRST_NAME)

        fun nextNickName(lastName: String): NickName {
            for (i in lastName.length - 1 downTo 0) {
                if (lastName[i] != 'Z') {
                    return NickName(
                        lastName.substring(0, i) + (lastName[i].code + 1).toChar() + "A".repeat(
                            lastName.length - (i + 1)
                        )
                    )
                }
            }
            return NickName("A".repeat(Math.max(0, lastName.length + 1)))
        }
    }
}
