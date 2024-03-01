package me.nalab.gallery.domain

import io.kotest.assertions.throwables.shouldThrowMessage
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.equality.shouldBeEqualUsingFields
import io.kotest.matchers.equals.shouldBeEqual
import me.nalab.core.time.TimeUtil
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ContextConfiguration
import java.time.temporal.ChronoUnit

@DataJpaTest
@EnableJpaRepositories
@EntityScan(value = ["me.nalab.core.data", "me.nalab.gallery.domain"])
@DisplayName("GalleryService 클래스의")
@ContextConfiguration(classes = [GalleryService::class])
internal class GalleryServiceTest(
    private val galleryService: GalleryService,
    private val galleryRepository: GalleryRepository,
) : DescribeSpec({

    afterEach {
        galleryRepository.deleteAll()
    }

    describe("registerGallery 메소드는") {
        beforeEach {
            galleryService.registerGallery(
                gallery(
                    id = EXIST_GALLERY_ID,
                    targetId = EXIST_TARGET_ID,
                    surveyId = EXIST_SURVEY_ID
                )
            )
        }

        context("Gallery에 등록되지 않은 target의 Gallery를 입력받으면,") {
            val gallery = gallery(targetId = NOT_EXIST_TARGET_ID)

            it("Gallery를 저장하고 반환한다.") {
                val result = galleryService.registerGallery(gallery)

                result shouldBeEqualUsingFields gallery
            }
        }

        context("이미 Gallery에 등록된 target의 Gallery를 입력받으면,") {
            val existGallery = gallery(targetId = EXIST_TARGET_ID)

            it("IllegalArgumentException을 던진다.") {
                shouldThrowMessage("target \"$EXIST_GALLERY_ID\" 이 이미 Gallery에 등록되어있습니다.") {
                    galleryService.registerGallery(existGallery)
                }
            }
        }
    }

    describe("getGalleries 메소드는") {
        beforeEach {
            galleryService.registerGallery(oldDesignerGallery)
            galleryService.registerGallery(midDeveloperGallery)
            galleryService.registerGallery(latestPmGallery)
        }

        context("job으로 all과 update순으로 정렬된 Pageable을 입력받으면,") {
            val expected = listOf(latestPmGallery, midDeveloperGallery, oldDesignerGallery)

            it("update순으로 정렬된 Gallery를 반환한다.") {
                val result = galleryService.getGalleries("all", updatePage).content

                result.shouldBeExactlyEqualToComparingFields(expected)
            }
        }

        context("job으로 designer와 update순으로 정렬된 Pageable을 입력받으면,") {
            val expected = listOf(oldDesignerGallery)

            it("update순으로 정렬된 designer Gallery를 반환한다.") {
                val result = galleryService.getGalleries("designer", updatePage).content

                result.shouldBeExactlyEqualToComparingFields(expected)
            }
        }

        context("job으로 pm과 update순으로 정렬된 Pageable을 입력받으면,") {
            val expected = listOf(latestPmGallery)

            it("update순으로 정렬된 pm Gallery를 반환한다.") {
                val result = galleryService.getGalleries("pm", updatePage).content

                result.shouldBeExactlyEqualToComparingFields(expected)
            }
        }

        context("job으로 developer와 update순으로 정렬된 Pageable을 입력받으면,") {
            val expected = listOf(midDeveloperGallery)

            it("update순으로 정렬된 pm Gallery를 반환한다.") {
                val result = galleryService.getGalleries("developer", updatePage).content

                result.shouldBeExactlyEqualToComparingFields(expected)
            }
        }

        context("job으로 all과 bookmark순으로 정렬된 Pageable을 받으면,") {
            val expected = listOf(oldDesignerGallery, midDeveloperGallery, latestPmGallery)

            it("bookmark순으로 정렬된 Gallery를 반환한다") {
                val result = galleryService.getGalleries("all", bookmarkPage).content

                result.shouldBeExactlyEqualToComparingFields(expected)
            }
        }

        context("job으로 designer와 bookmark순으로 정렬된 Pageable을 입력받으면,") {
            val expected = listOf(oldDesignerGallery)

            it("update순으로 정렬된 designer Gallery를 반환한다.") {
                val result = galleryService.getGalleries("designer", bookmarkPage).content

                result.shouldBeExactlyEqualToComparingFields(expected)
            }
        }

        context("job으로 pm과 bookmark순으로 정렬된 Pageable을 입력받으면,") {
            val expected = listOf(latestPmGallery)

            it("update순으로 정렬된 pm Gallery를 반환한다.") {
                val result = galleryService.getGalleries("pm", bookmarkPage).content

                result.shouldBeExactlyEqualToComparingFields(expected)
            }
        }

        context("job으로 developer와 bookmark순으로 정렬된 Pageable을 입력받으면,") {
            val expected = listOf(midDeveloperGallery)

            it("update순으로 정렬된 pm Gallery를 반환한다.") {
                val result = galleryService.getGalleries("developer", bookmarkPage).content

                result.shouldBeExactlyEqualToComparingFields(expected)
            }
        }
    }
}) {

    companion object {
        private const val NOT_EXIST_TARGET_ID = 1L
        private const val EXIST_GALLERY_ID = 100L
        private const val EXIST_TARGET_ID = 100L
        private const val EXIST_SURVEY_ID = 100L

        val updatePage: PageRequest = PageRequest.of(0, 5, Sort.by("updateOrder").descending())
        val bookmarkPage: PageRequest =
            PageRequest.of(0, 5, Sort.by("survey.bookmarkedCount").descending())

        val oldDesignerGallery = gallery(
            id = 1,
            targetId = 101,
            surveyId = 101,
            job = Job.DESIGNER,
            updateOrder = TimeUtil.toInstant().minus(1, ChronoUnit.DAYS),
            bookmarkedCount = 3
        )

        val midDeveloperGallery = gallery(
            id = 2,
            targetId = 102,
            surveyId = 102,
            job = Job.DEVELOPER,
            updateOrder = TimeUtil.toInstant(),
            bookmarkedCount = 2
        )

        val latestPmGallery = gallery(
            id = 3,
            targetId = 103,
            surveyId = 103,
            job = Job.PM,
            updateOrder = TimeUtil.toInstant().plus(1, ChronoUnit.DAYS),
            bookmarkedCount = 1
        )

        private fun List<Gallery>.shouldBeExactlyEqualToComparingFields(galleries: List<Gallery>) {
            this.size shouldBeEqual galleries.size
            for (i in galleries.indices) {
                this[i] shouldBeEqualToComparingFields galleries[i]
            }
        }
    }
}
