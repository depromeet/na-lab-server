package me.nalab.core.authorization.aop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.core.authorization.aop.util.ValidatorFactoryStorage;
import me.nalab.core.authorization.aop.validator.LongValidorFactory;
import me.nalab.core.authorization.engine.CannotAuthorizationException;
import me.nalab.core.authorization.spring.Configurer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Configurer.class,
	AuthorizationAspect.class,
	ValidatorFactoryStorage.class,
	AspectTarget.class,
	LongValidorFactory.class
})
class AuthorizationAspectTest {

	@Autowired
	private AuthorizationAspect authorizationAspect;

	@MockBean
	private MockHttpServletRequest mockHttpServletRequest;

	@Test
	@DisplayName("하나의 @Auth가 있을때, 인증에 성공한다.")
	void AUTHORIZATION_WITH_SINGLE_AUTH() {
		// given
		Long parameter = 1L;
		AspectTarget aspectTarget = getAspectTarget();

		when(mockHttpServletRequest.getAttribute("logined")).thenReturn(parameter);

		// when
		Long result = aspectTarget.isArrived(parameter);

		// then
		assertThat(result).isEqualTo(parameter);
	}

	@Test
	@DisplayName("두개의 @Auth가 있을때, 첫번째 Auth 기준으로 인증하면 성공한다.")
	void AUTHORIZATION_WITH_TWO_AUTH_FIRST_PARAMETER() {
		// given
		Long parameter1 = 1L;
		Long parameter2 = 2L;
		AspectTarget aspectTarget = getAspectTarget();

		when(mockHttpServletRequest.getAttribute("logined")).thenReturn(parameter1);

		// when
		Long result = aspectTarget.isArrived(parameter1, parameter2);

		// then
		assertThat(result).isEqualTo(parameter1);
	}

	@Test
	@DisplayName("두개의 @Auth가 있을때, 두번째 Auth 기준으로 인증하면 실패한다.")
	void AUTHORIZATION_WITH_TWO_SECOND_PARAMETER() {
		// given
		Long parameter1 = 1L;
		Long parameter2 = 2L;
		AspectTarget aspectTarget = getAspectTarget();

		when(mockHttpServletRequest.getAttribute("logined")).thenReturn(parameter1);

		// when
		Exception result = catchException(() -> aspectTarget.isArrived(parameter2, parameter1));

		// then
		assertThat(result.getClass()).isEqualTo(CannotAuthorizationException.class);
	}

	@Test
	@DisplayName("@Auth 어노테이션이 없을때, 실패한다.")
	void AUTHORIZATION_WITH_NO_AUTH_ANNOTATION() {
		// given
		Long parameter = 1L;
		AspectTarget aspectTarget = getAspectTarget();

		when(mockHttpServletRequest.getAttribute("logined")).thenReturn(parameter);

		// when
		Exception result = catchException(() -> aspectTarget.isArrivedWithOutAuth(parameter));

		// then
		assertThat(result.getClass()).isEqualTo(IllegalStateException.class);
	}

	private AspectTarget getAspectTarget() {
		AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(new AspectTarget());
		aspectJProxyFactory.addAspect(authorizationAspect);
		return aspectJProxyFactory.getProxy();
	}

}
