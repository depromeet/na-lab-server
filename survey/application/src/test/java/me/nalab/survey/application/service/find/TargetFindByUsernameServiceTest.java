package me.nalab.survey.application.service.find;

import me.nalab.survey.application.port.out.persistence.target.find.TargetFindPort;
import me.nalab.survey.application.service.TargetFindByUsernameService;
import me.nalab.survey.domain.target.Target;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TargetFindByUsernameService.class)
class TargetFindByUsernameServiceTest {

    @Autowired
    private TargetFindByUsernameService targetFindByUsernameService;

    @MockBean
    private TargetFindPort targetFindPort;

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("username이 빈 값이거나 없다면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_NULL_OR_EMPTY_USERNAME(String username) {
        // given
        // when
        var throwable = Assertions.catchThrowable(() -> targetFindByUsernameService.findTargetByUsername(username));

        // then
        Assertions.assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("username에 해당하는 유저가 없다면, empty 값을 반환한다")
    void RETURN_EMPTY_WHEN_NOT_EXIST_TARGET_BY_USERNAME() {
        // given
        var username = "username";
        when(targetFindPort.findAllByUsername(any())).thenReturn(Collections.emptyList());

        // when
        var result = targetFindByUsernameService.findTargetByUsername(username);

        // then
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("username에 해당하는 타겟이 하나라면, 해당 타겟 dto 값을 반환한다")
    void RETURN_TARGET_DTO_WHEN_EXIST_ONLY_ONE_TARGET_BY_USERNAME() {
        // given
        var username = "username";
        var target = Target.builder()
                .id(1L)
                .nickname(username)
                .build();
        when(targetFindPort.findAllByUsername(any())).thenReturn(List.of(target));

        // when
        var result = targetFindByUsernameService.findTargetByUsername(username);

        // then
        Assertions.assertThat(result).isPresent();
        var dto = result.get();
        Assertions.assertThat(dto.getId()).isEqualTo(target.getId());
        Assertions.assertThat(dto.getNickname()).isEqualTo(target.getNickname());
    }

    @Test
    @DisplayName("username에 해당하는 타겟이 여러 개라면, 가장 마지막 dto 값을 반환한다")
    void RETURN_LATEST_TARGET_DTO_WHEN_EXIST_MANY_TARGET_BY_USERNAME() {
        // given
        var username = "username";
        var maxId = 10;
        var targetList = LongStream.rangeClosed(0, maxId)
                .mapToObj(id ->
                        Target.builder()
                                .id(id)
                                .nickname(username)
                                .build()
                ).collect(Collectors.toList());
        when(targetFindPort.findAllByUsername(any())).thenReturn(targetList);

        // when
        var result = targetFindByUsernameService.findTargetByUsername(username);

        // then
        Assertions.assertThat(result).isPresent();
        var dto = result.get();
        Assertions.assertThat(dto.getId()).isEqualTo(maxId);
        Assertions.assertThat(dto.getNickname()).isEqualTo(username);
    }
}
