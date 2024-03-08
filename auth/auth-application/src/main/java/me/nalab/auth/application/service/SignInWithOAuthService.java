package me.nalab.auth.application.service;

import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.common.dto.AuthToken;
import me.nalab.auth.application.common.dto.CreateAuthTokenRequest;
import me.nalab.auth.application.common.dto.SignInWithOAuthRequest;
import me.nalab.auth.application.common.dto.SignUpWithOAuthRequest;
import me.nalab.auth.application.port.in.web.AuthTokenCreateUseCase;
import me.nalab.auth.application.port.in.web.SignInWithOAuthUseCase;
import me.nalab.auth.application.port.in.web.SignUpWithOAuthUseCase;
import me.nalab.survey.application.port.in.web.target.find.TargetFindByUsernameUseCase;
import me.nalab.user.application.common.dto.FindByProviderAndTokenRequest;
import me.nalab.user.application.port.in.UserFindByProviderAndTokenUseCase;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignInWithOAuthService implements SignInWithOAuthUseCase {

    private final UserFindByProviderAndTokenUseCase userFindByProviderAndTokenUseCase;
    private final TargetFindByUsernameUseCase targetFindByUsernameUseCase;
    private final SignUpWithOAuthUseCase signUpWithOAuthUseCase;
    private final AuthTokenCreateUseCase authTokenCreateUseCase;

    @Override
    public AuthToken signInWithOAuth(SignInWithOAuthRequest request) {
        var providerName = request.getProviderName();
        var email = request.getEmail();
        Assert.isTrue(providerName != null && !providerName.isBlank(), "OAuth를 이용한 SignIn은 제공자의 이름 값은 필수입니다.");
        Assert.isTrue(email != null && !email.isBlank(), "OAuth를 이용한 SignIn은 이메일 값은 필수입니다.");

        var foundUser = findUserIdAndSignUpIfNeeded(request, providerName, email);
        var userId = foundUser.orElseThrow(IllegalAccessError::new);
        var targetId = targetFindByUsernameUseCase.findTargetByUsername(request.getUsername()).orElseThrow().getId();

        var authTokenCreateRequest = new CreateAuthTokenRequest(userId.toString(), request.getUsername(), String.valueOf(targetId), email);

        return authTokenCreateUseCase.create(authTokenCreateRequest);
    }

    private Optional<Long> findUserIdAndSignUpIfNeeded(SignInWithOAuthRequest request, String providerName, String email) {
        var userFindRequest = new FindByProviderAndTokenRequest.In(providerName, email);
        var foundUser = userFindByProviderAndTokenUseCase.findByProviderAndToken(userFindRequest);
        if (foundUser.isPresent()) {
            return foundUser;
        }

        // 회원가입이 되어있지 않다면, 회원 가입 후 다시 조회한다.
        var signUpRequest = SignUpWithOAuthRequest.builder()
                .providerName(providerName)
                .email(email)
                .username(request.getUsername())
                .phoneNumber(request.getPhoneNumber())
                .build();
        var createdUserId = signUpWithOAuthUseCase.signUpWithOAuth(signUpRequest);
        return Optional.of(createdUserId);
    }
}
