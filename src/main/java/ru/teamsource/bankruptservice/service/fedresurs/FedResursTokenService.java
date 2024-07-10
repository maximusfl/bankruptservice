package ru.teamsource.bankruptservice.service.fedresurs;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.teamsource.bankruptservice.model.Token;
import ru.teamsource.bankruptservice.model.request.GetTokenRq;
import ru.teamsource.bankruptservice.model.response.GetTokenRs;

@Service
@RequiredArgsConstructor
public class FedResursTokenService implements TokenService {
    @Value("fedresurs-url")
    private String fedResurseUrl;
    @Value("fedresurs-user")
    private String user;
    @Value("fedresurs-password")
    private String pwd;
    private final FedResursClient fedResursClient;
    private final Token token;

    @Override
    public Token getToken() {
        if (token.isExpired()) {
            refreshToken();
        }
        return this.token;
    }

    @PostConstruct
    void initToken() {
        refreshToken();
    }

    private void refreshToken() {
        fedResursClient.post(fedResurseUrl, new GetTokenRq(user, pwd), GetTokenRs.class);
    }
}
