package me.nalab.api.survey.domain.survey

import me.nalab.core.data.common.TimeBaseEntity
import javax.persistence.*

@Entity
@Table(name = "form_question")
abstract class FormQuestionable(
    @Id
    @Column(name = "form_question_id")
    open val id: Long,

    @Column(name = "title", nullable = false, length = 45)
    open val title: String,

    @Column(name = "orders", nullable = false)
    open val order: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type")
    open val questionType: QuestionType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = false)
    open val survey: Survey,
) : Comparable<FormQuestionable>, TimeBaseEntity() {

    override fun compareTo(other: FormQuestionable): Int {
        return Comparator.comparingInt { obj: FormQuestionable -> obj.order }
            .compare(this, other)
    }
}
