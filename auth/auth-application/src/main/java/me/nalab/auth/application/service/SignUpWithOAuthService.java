package me.nalab.auth.application.service;

import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.common.dto.SignUpWithOAuthRequest;
import me.nalab.auth.application.port.in.web.SignUpWithOAuthUseCase;
import me.nalab.user.application.common.dto.CreateUserWithOAuthRequest;
import me.nalab.user.application.common.dto.FindByProviderAndTokenRequest;
import me.nalab.user.application.port.in.UserCreateWithOAuthUseCase;
import me.nalab.user.application.port.in.UserFindByProviderAndTokenUseCase;
import me.nalab.user.domain.user.Provider;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class SignUpWithOAuthService implements SignUpWithOAuthUseCase {

    private final UserFindByProviderAndTokenUseCase userFindByProviderAndTokenUseCase;
    private final UserCreateWithOAuthUseCase userCreateWithOAuthUseCase;

    @Override
    public void signUpWithOAuth(SignUpWithOAuthRequest request) {
        var providerName = request.getProviderName();
        var email = request.getEmail();
        Assert.isTrue(providerName != null && !providerName.isBlank(), "OAuth를 이용한 SignIn은 제공자의 이름 값은 필수입니다.");
        Assert.isTrue(email != null && !email.isBlank(), "OAuth를 이용한 SignIn은 이메일 값은 필수입니다.");

        var inRequest = new FindByProviderAndTokenRequest.In(providerName, email);
        var foundUser = userFindByProviderAndTokenUseCase.findByProviderAndToken(inRequest);

        if (foundUser.isPresent()) {
            return;
        }

        var provider = Provider.valueOf(providerName);
        var createUserRequest = new CreateUserWithOAuthRequest(
                provider,
                email,
                request.getUsername(),
                request.getPhoneNumber()
        );

        userCreateWithOAuthUseCase.createUser(createUserRequest);
    }

}
