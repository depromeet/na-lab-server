package me.nalab.survey.domain.survey

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.JoinColumn

@Embeddable
class SurveyBookmark(
    @Column(name = "bookmarked_survey_id")
    @JoinColumn(name = "survey_id", nullable = false)
    val surveyId: Long
)
