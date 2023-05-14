package me.nalab.auth.mock.api;

import lombok.Builder;
import lombok.Getter;

/**
 * 인증이 필요한 API를 테스트 할때, 이 Event를 Publish하면,
 * expectedToken이 Authorization 헤더로 주어졌을때, expectedId를
 * Controller의 @RequestAttribute("logined") 로 받을 수 있습니다.
 */
@Builder
@Getter
public class MockUserRegisterEvent {

	private final Long expectedId;
	private final String expectedToken;

}
