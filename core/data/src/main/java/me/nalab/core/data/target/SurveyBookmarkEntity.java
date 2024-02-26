package me.nalab.core.data.target;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SurveyBookmarkEntity {

    @Column(name = "bookmarked_survey_id")
    @JoinColumn(name = "survey_id", nullable = false)
    private Long surveyId;

}
