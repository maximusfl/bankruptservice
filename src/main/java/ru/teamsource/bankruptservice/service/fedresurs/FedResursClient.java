package ru.teamsource.bankruptservice.service.fedresurs;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class FedResursClient {
    private final RestTemplate restTemplate;


    public <R> ResponseEntity<R> get(String url, Class<R> responseType) {
        return restTemplate.getForEntity(url, responseType);
    }

    public <T, R> ResponseEntity<R> post(String url, T request, Class<R> responseType) {
        return restTemplate.postForEntity(url, request, responseType);
    }
}
