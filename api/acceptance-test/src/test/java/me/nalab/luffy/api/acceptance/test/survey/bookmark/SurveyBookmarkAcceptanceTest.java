package me.nalab.luffy.api.acceptance.test.survey.bookmark;

import static me.nalab.luffy.api.acceptance.test.survey.SurveyAcceptanceValidator.assertIsBookmarked;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import me.nalab.auth.mock.api.MockUserRegisterEvent;
import me.nalab.luffy.api.acceptance.test.TargetInitializer;
import me.nalab.luffy.api.acceptance.test.survey.AbstractSurveyTestSupporter;
import me.nalab.luffy.api.acceptance.test.survey.RequestSample;
import me.nalab.survey.jpa.adaptor.findid.repository.SurveyIdFindJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:h2.properties")
@ComponentScan("me.nalab")
@EnableJpaRepositories(basePackages = {"me.nalab"})
@EntityScan(basePackages = {"me.nalab"})
class SurveyBookmarkAcceptanceTest extends AbstractSurveyTestSupporter {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private TargetInitializer targetInitializer;

    @Autowired
    private SurveyIdFindJpaRepository surveyIdFindJpaRepository;

    @Test
    @DisplayName("surveyBookmark api 는 token의 주인에게 survey를 북마크한다.")
    void BOOKMARK_SURVEY_TO_TOKEN_OWNER() throws Exception {
        // given
        var targetId = targetInitializer.saveTargetAndGetId("luffy",
            LocalDateTime.now().minusYears(24).toInstant(ZoneOffset.UTC));
        var token = "bearer luffy's-double-token";
        applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
            .expectedToken(token)
            .expectedId(targetId)
            .build());
        createSurvey(token, RequestSample.DEFAULT_JSON);

        var surveyId = getSurveyId(targetId);

        // when
        var result = bookmarkSurvey(token, surveyId);

        // then
        assertIsBookmarked(result);
    }

    private Long getSurveyId(Long targetId) {
        return surveyIdFindJpaRepository.findAllIdByTargetId(targetId).get(0);
    }
}
