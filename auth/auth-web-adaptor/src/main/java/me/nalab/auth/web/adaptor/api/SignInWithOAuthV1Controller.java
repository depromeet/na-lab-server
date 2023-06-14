package me.nalab.auth.web.adaptor.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.common.dto.AuthToken;
import me.nalab.auth.application.common.dto.SignInWithOAuthRequest;
import me.nalab.auth.application.port.in.web.SignInWithOAuthUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/v1/oauth")
@RequiredArgsConstructor
public class SignInWithOAuthV1Controller {

    private final SignInWithOAuthUseCase signInWithOauthUseCase;

    private static final String TOKEN_TYPE = "bearer";


    @PostMapping("/{oauth-provider}")
    public Response signInWithOAuth(
            @PathVariable("oauth-provider") String providerName,
            @RequestBody Request request
    ) {
        var useCaseRequest = createUseCaseRequest(providerName, request);
        var authToken = signInWithOauthUseCase.signInWithOAuth(useCaseRequest);

        return createResponse(authToken);
    }

    private Response createResponse(AuthToken authToken) {
        return new Response(TOKEN_TYPE, authToken.getToken());
    }

    private SignInWithOAuthRequest createUseCaseRequest(String providerName, Request request) {
        return SignInWithOAuthRequest.builder()
                .providerName(providerName.toUpperCase())
                .email(request.email)
                .username(request.nickname)
                .build();
    }

    @Data
    public static class Request {
        @NotBlank
        private final String nickname;
        @Email
        private final String email;
    }

    @Data
    public static class Response {
        @JsonProperty("token_type")
        private final String tokenType;
        @JsonProperty("access_token")
        private final String accessToken;
    }

}
