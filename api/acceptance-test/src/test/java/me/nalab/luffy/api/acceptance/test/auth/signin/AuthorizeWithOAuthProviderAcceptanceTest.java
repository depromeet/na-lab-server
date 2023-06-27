package me.nalab.luffy.api.acceptance.test.auth.signin;

import me.nalab.user.domain.user.Provider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:h2.properties")
@ComponentScan("me.nalab")
@EnableJpaRepositories(basePackages = {"me.nalab"})
@EntityScan(basePackages = {"me.nalab"})
class AuthorizeWithOAuthProviderAcceptanceTest extends AbstractAuthTestSupporter {

	@Test
	@DisplayName("provider에 대한 인증 요청 성공")
	@Transactional
	void GET_AUTHORIZE_URL_WHEN_VALID_PROVIDER() throws Exception {
		// given
		var providerName = Provider.KAKAO.name();

		// when
		ResultActions resultActions = getAuthorizeByOAuthProvider(providerName);

		// then
		resultActions.andExpect(status().is3xxRedirection());
	}

}
