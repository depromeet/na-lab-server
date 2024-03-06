package me.nalab.survey.domain.survey

import me.nalab.core.data.common.TimeBaseEntity
import java.util.function.LongSupplier
import javax.persistence.*

@Entity
@Table(name = "form_question")
abstract class FormQuestionable(
    @Id
    @Column(name = "form_question_id")
    open var id: Long? = null,

    @Column(name = "title", nullable = false, length = 45)
    protected open val title: String,

    @Column(name = "orders", nullable = false)
    open val order: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type")
    protected open val questionType: QuestionType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = false)
    open val survey: Survey,
) : Comparable<FormQuestionable>, TimeBaseEntity() {

    protected open fun cascadeId(idSupplier: LongSupplier) {
        return
    }

    override fun compareTo(other: FormQuestionable): Int {
        return Comparator.comparingInt { obj: FormQuestionable -> obj.order }
            .compare(this, other)
    }
}
