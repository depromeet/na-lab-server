package me.nalab.gallery.app

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equality.shouldBeEqualUsingFields
import io.mockk.every
import me.nalab.gallery.domain.galleryPreviewDto
import me.nalab.gallery.domain.galleryPreviewDtoSurvey
import me.nalab.gallery.domain.galleryPreviewDtoTarget
import me.nalab.survey.application.port.`in`.web.findfeedback.FeedbackFindUseCase
import me.nalab.survey.application.port.`in`.web.survey.find.SurveyFindUseCase
import me.nalab.survey.application.port.`in`.web.target.find.TargetFindUseCase
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(
    classes = [
        GalleryPreviewApp::class,
    ]
)
@DisplayName("GalleryPreviewApp 의")
internal class GalleryPreviewAppTest(
    private val galleryPreviewApp: GalleryPreviewApp,
    @MockkBean(relaxed = true) private val targetFindUseCase: TargetFindUseCase,
    @MockkBean(relaxed = true) private val surveyFindUseCase: SurveyFindUseCase,
    @MockkBean(relaxed = true) private val feedbackFindUseCase: FeedbackFindUseCase,
) : DescribeSpec({

    beforeEach {
        every { targetFindUseCase.findTarget(DEFAULT_TARGET_ID) } returns defaultTargetDto
        every { surveyFindUseCase.getSurveyByTargetId(DEFAULT_TARGET_ID) } returns defaultSurveyDto
        every { feedbackFindUseCase.findAllFeedbackDtoBySurveyId(DEFAULT_SURVEY_ID) } returns listOf(
            defaultFeedbackDto
        )
    }

    describe("getGalleryPreview 메소드는") {
        context("존재하는 타겟의 id를 받으면,") {
            val expected = galleryPreviewDto(
                target = galleryPreviewDtoTarget(DEFAULT_TARGET_ID.toString()),
                survey = galleryPreviewDtoSurvey(
                    surveyId = DEFAULT_SURVEY_ID.toString(),
                    bookmarkedCount = 0
                ),
            )

            it("GalleryPreviewDto를 반환한다.") {
                val response = galleryPreviewApp.findGalleryPreview(DEFAULT_TARGET_ID)

                response shouldBeEqualUsingFields expected
            }
        }

        context("피드백이 하나도 없다면,") {
            val expected = galleryPreviewDto(
                target = galleryPreviewDtoTarget(DEFAULT_TARGET_ID.toString()),
                survey = galleryPreviewDtoSurvey(
                    surveyId = DEFAULT_SURVEY_ID.toString(),
                    feedbackCount = 0,
                    bookmarkedCount = 0,
                    feedbacks = emptyList(),
                    tendencies = emptyList(),
                )
            )


            it("feedback이 비어있는 GalleryPreviewDto를 반환한다.") {
                every { surveyFindUseCase.getSurveyByTargetId(DEFAULT_TARGET_ID) } returns surveyDto(
                    id = DEFAULT_SURVEY_ID, formQuestionDtos = listOf(choiceFormQuestionDto())
                )
                every { feedbackFindUseCase.findAllFeedbackDtoBySurveyId(DEFAULT_SURVEY_ID) } returns emptyList()

                val response = galleryPreviewApp.findGalleryPreview(DEFAULT_TARGET_ID)

                response shouldBeEqualUsingFields expected
            }
        }
    }

}) {
    private companion object {
        private const val DEFAULT_TARGET_ID = 1L
        private const val DEFAULT_SURVEY_ID = 2L
        private const val DEFAULT_FEEDBACK_ID = 3L
        private const val TENDENCY_QUESTION_ID = 4L
        private const val CUSTOM_SHORT_FORM_QUESTION_ID = 5L

        private val defaultTargetDto = targetDto(id = DEFAULT_TARGET_ID)

        private val formQuestionDtos = listOf(
            choiceFormQuestionDto(id = TENDENCY_QUESTION_ID),
            shortFormQuestionDto(id = CUSTOM_SHORT_FORM_QUESTION_ID),
        )

        private val defaultSurveyDto =
            surveyDto(id = DEFAULT_SURVEY_ID, formQuestionDtos = formQuestionDtos)

        private val formQuestionFeedbackDtos = listOf(
            choiceFormQuestionFeedbackDto(questionId = TENDENCY_QUESTION_ID),
            shortFormQuestionFeedbackDto(
                questionId = CUSTOM_SHORT_FORM_QUESTION_ID,
                bookmarkDto = bookmarkDto(isBookmarked = true)
            ),
        )

        private val defaultFeedbackDto =
            feedbackDto(
                id = DEFAULT_FEEDBACK_ID,
                surveyId = DEFAULT_SURVEY_ID,
                formQuestionFeedbackDtos = formQuestionFeedbackDtos,
            )
    }
}
