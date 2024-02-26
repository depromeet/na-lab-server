package me.nalab.gallery.app

import me.nalab.gallery.domain.response.GalleryPreviewDto
import me.nalab.survey.application.port.`in`.web.findfeedback.FeedbackFindUseCase
import me.nalab.survey.application.port.`in`.web.survey.find.SurveyFindUseCase
import me.nalab.survey.application.port.`in`.web.target.find.TargetFindUseCase
import org.springframework.stereotype.Service

@Service
class GalleryPreviewApp(
    private val targetFindUseCase: TargetFindUseCase,
    private val surveyFindUseCase: SurveyFindUseCase,
    private val feedbackFindUseCase: FeedbackFindUseCase,
) {

    fun findGalleryPreview(targetId: Long): GalleryPreviewDto {
        val target = targetFindUseCase.findTarget(targetId)
        val survey = surveyFindUseCase.getSurveyByTargetId(targetId)
        val feedbacks = feedbackFindUseCase.findAllFeedbackDtoBySurveyId(survey.id)

        return toGalleryPreviewDto(target, survey, feedbacks)
    }
}
