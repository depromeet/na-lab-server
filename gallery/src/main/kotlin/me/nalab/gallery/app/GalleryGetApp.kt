package me.nalab.gallery.app

import me.nalab.gallery.domain.Gallery
import me.nalab.gallery.domain.GalleryService
import me.nalab.gallery.domain.response.GalleriesDto
import me.nalab.gallery.domain.response.GalleryDto
import me.nalab.survey.application.port.`in`.web.findfeedback.FeedbackFindUseCase
import me.nalab.survey.application.port.`in`.web.survey.find.SurveyFindUseCase
import me.nalab.survey.application.port.`in`.web.target.find.TargetFindUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class GalleryGetApp(
    private val targetFindUseCase: TargetFindUseCase,
    private val surveyFindUseCase: SurveyFindUseCase,
    private val feedbackFindUseCase: FeedbackFindUseCase,
    private val galleryService: GalleryService,
) {

    fun getGalleryByTargetId(targetId: Long): GalleryDto {
        val gallery = galleryService.getGalleryByTargetId(targetId)
        val target = targetFindUseCase.findTarget(targetId)
        val survey = surveyFindUseCase.getSurveyByTargetId(targetId)
        val feedbacks = feedbackFindUseCase.findAllFeedbackDtoBySurveyId(survey.id)

        return toGalleryDto(gallery, target, survey, feedbacks)
    }

    fun getGalleries(job: String, page: Int, count: Int, orderType: String): GalleriesDto {
        val pageable = getPage(page, count, orderType)
        val galleries = galleryService.getGalleries(job, pageable)

        val galleryDtos = toGalleryDtos(galleries)

        return GalleriesDto(galleries.totalPages, galleryDtos)
    }

    private fun toGalleryDtos(galleries: Page<Gallery>): List<GalleryDto> {
        return galleries.asSequence()
            .map { gallery ->
                val target = targetFindUseCase.findTarget(gallery.getTargetId())
                val survey = surveyFindUseCase.getSurveyByTargetId(gallery.getTargetId())
                val feedbacks = feedbackFindUseCase.findAllFeedbackDtoBySurveyId(survey.id)

                toGalleryDto(gallery, target, survey, feedbacks)
            }.toList()
    }

    private fun getPage(page: Int, count: Int, orderType: String): Pageable {
        return when (orderType.lowercase()) {
            "update" -> PageRequest.of(page, count, Sort.by("updateOrder").descending())
            "job" -> PageRequest.of(page, count, Sort.by("survey.bookmarkedCount").descending())
            else -> throw IllegalArgumentException("orderType 은 update와 bookmark중 하나여야 합니다. 현재 orderType \"$orderType\"")
        }
    }
}
