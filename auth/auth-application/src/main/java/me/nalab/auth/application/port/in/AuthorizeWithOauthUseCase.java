package me.nalab.auth.application.port.in;

import java.net.URI;

public interface AuthorizeWithOauthUseCase {
    URI getAuthorizationUrl(String providerName);
}
