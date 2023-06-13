package me.nalab.auth.web.adaptor.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.common.dto.AuthToken;
import me.nalab.auth.application.common.dto.OAuthAccessTokenRequest;
import me.nalab.auth.application.common.dto.OAuthAccessTokenResponse;
import me.nalab.auth.application.common.dto.OAuthResourceRequest;
import me.nalab.auth.application.common.dto.OAuthResourceResponse;
import me.nalab.auth.application.common.dto.SignInWithOAuthRequest;
import me.nalab.auth.application.port.in.web.OAuthGetAccessTokenUseCase;
import me.nalab.auth.application.port.in.web.OAuthGetResourceUseCase;
import me.nalab.auth.application.port.in.web.SignInWithOAuthUseCase;
import me.nalab.auth.web.adaptor.exception.UserOAuthRejectException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * authorized 권한 획득 후 SignIn 요청이 들어오는 api
 * @see me.nalab.auth.application.service.AuthorizeWithOauthService
 */
@RestController
@RequestMapping("/v1/oauth")
@RequiredArgsConstructor
public class SignInWithOAuthController {

    private final OAuthGetAccessTokenUseCase oAuthGetAccessTokenUseCase;
    private final OAuthGetResourceUseCase oauthGetResourceUseCase;
    private final SignInWithOAuthUseCase signInWithOauthUseCase;

    private static final String TOKEN_TYPE = "bearer";

    @PostMapping("/{oauth-provider}")
    public Response signInWithOAuth(
            @PathVariable("oauth-provider") String providerName,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "error_description", required = false) String errorDescription
    ) {
        if (code.isBlank()) {
            var errorMessage = "["+error+"]"+errorDescription;
            throw new UserOAuthRejectException(errorMessage);
        }

        var accessTokenRequest = new OAuthAccessTokenRequest(providerName, code);
        var accessTokenResponse = oAuthGetAccessTokenUseCase.get(accessTokenRequest);

        var resourceRequest = getResourceRequest(providerName, accessTokenResponse);
        var resourceResponse = oauthGetResourceUseCase.get(resourceRequest);

        var signInRequest = createSignInRequest(providerName, resourceResponse);
        var authToken = signInWithOauthUseCase.signInWithOAuth(signInRequest);

        return createResponse(authToken);
    }

    private OAuthResourceRequest getResourceRequest(String providerName, OAuthAccessTokenResponse accessTokenResponse) {
        return new OAuthResourceRequest(
                providerName,
                accessTokenResponse.getToken(),
                accessTokenResponse.getTokenType()
        );
    }

    private SignInWithOAuthRequest createSignInRequest(String providerName, OAuthResourceResponse response) {
        return SignInWithOAuthRequest.builder()
                .providerName(providerName.toUpperCase())
                .email(response.getEmail())
                .username(response.getNickname())
                .build();
    }

    private Response createResponse(AuthToken authToken) {
        return new Response(TOKEN_TYPE, authToken.getToken());
    }

    @Data
    public static class Response {
        @JsonProperty("token_type")
        private final String tokenType;
        @JsonProperty("access_token")
        private final String accessToken;
    }

}
