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

@Service
@RequiredArgsConstructor
public class SignUpWithOAuthService implements SignUpWithOAuthUseCase {

    private final UserFindByProviderAndTokenUseCase userFindByProviderAndTokenUseCase;
    private final UserCreateWithOAuthUseCase userCreateWithOAuthUseCase;

    @Override
    public void signUpWithOAuth(SignUpWithOAuthRequest request) {
        var inRequest = new FindByProviderAndTokenRequest.In(request.getProviderName(), request.getEmail());
        var foundUser = userFindByProviderAndTokenUseCase.findByProviderAndToken(inRequest);

        if (foundUser.isPresent()) return;

        var provider = Provider.valueOf(request.getProviderName());
        var createUserRequest = new CreateUserWithOAuthRequest(
                provider,
                request.getEmail(),
                request.getUsername(),
                request.getPhoneNumber()
        );

        userCreateWithOAuthUseCase.createUser(createUserRequest);
    }

}
