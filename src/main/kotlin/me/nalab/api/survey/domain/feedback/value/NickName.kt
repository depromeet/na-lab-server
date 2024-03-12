package me.nalab.api.survey.domain.feedback.value

import javax.persistence.Embeddable

@JvmInline
value class NickName(
    val value: String
) {

    companion object {
        private const val FIRST_NAME = "A"
        fun nextNickName(lastNickName: String?): NickName {
            if (lastNickName == null) {
                return NickName(FIRST_NAME)
            }

            if (lastNickName.last() < 'Z') {
                return NickName(lastNickName.dropLast(1).plus(lastNickName.last() + 1))
            }
            val sb = StringBuilder()
            for (i in lastNickName.lastIndex downTo 0) {
                val expectedChar = lastNickName[i] + 1
                if (expectedChar <= 'Z') {
                    sb.append(expectedChar)
                    sb.append(lastNickName.substring(0, i))
                    break
                }
                sb.append("A")
                // 첫자리까지 Z보다 크다면 앞에 A를 추가해준다.
                if (i == 0) {
                    sb.append("A")
                }
            }
            return NickName(sb.reverse().toString())
        }
    }
}
