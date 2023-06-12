package me.nalab.auth.web.adaptor.api;

import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.port.in.AuthorizeWithOauthUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller("/v1/auth")
@RequiredArgsConstructor
public class AuthorizeWithOAuthProviderController {

    private final AuthorizeWithOauthUseCase authorizeWithOauthUseCase;

    @GetMapping("/oauth/{oauth-provider}")
    public RedirectView authorizeByOAuthProvider(
            @PathVariable("oauth-provider") String providerName
    ) {
        var authorizationUrl = authorizeWithOauthUseCase.getAuthorizationUrl(providerName);
        return new RedirectView(authorizationUrl.toASCIIString());
    }

}
