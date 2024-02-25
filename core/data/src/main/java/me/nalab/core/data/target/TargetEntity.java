package me.nalab.core.data.target;

import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.nalab.core.data.common.TimeBaseEntity;

@Entity
@Table(name = "target")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TargetEntity extends TimeBaseEntity {

	@Id
	@Column(name = "target_id")
	private Long id;

	@Column(name = "target_name", nullable = false)
	private String nickname;

	@Column(name = "position")
	private String position;

    @Column(name = "job", columnDefinition = "TEXT")
    private String job;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @ElementCollection
    @CollectionTable(
        name = "bookmarked_survey",
        joinColumns = @JoinColumn(name = "target_id")
    )
    private Set<SurveyBookmarkEntity> bookmarkedSurveys;

    @Version
    @Column(name = "version")
    private Long version;

}
