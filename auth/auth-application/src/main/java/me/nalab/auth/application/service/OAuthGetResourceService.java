package me.nalab.auth.application.service;

import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.common.dto.OAuthResourceRequest;
import me.nalab.auth.application.common.dto.OAuthResourceResponse;
import me.nalab.auth.application.port.in.web.OAuthGetResourceUseCase;
import me.nalab.auth.application.port.out.OAuthWebClientFactory;
import me.nalab.user.domain.user.Provider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthGetResourceService implements OAuthGetResourceUseCase {

    private final OAuthWebClientFactory oAuthWebClientFactory;

    @Override
    public OAuthResourceResponse get(OAuthResourceRequest request) {
        var provider = Provider.valueOf(request.getProviderName().toUpperCase());
        var webClient = oAuthWebClientFactory.getClient(provider);

        return webClient.getResource(request.getToken());
    }

}
