package com.example.hello.aqi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class AqicnGateway {
	
	private final RestTemplate restTemplate;
    private final String url;
    private final String token;

    @Autowired
    public AqicnGateway(final RestTemplate restTemplate,
                         @Value("${aqicn.org.url}") final String url,
                         @Value("${aqicn.org.token}") final String token) {
        this.restTemplate = restTemplate;
        this.url = url;
        this.token = token;
    }

    public Optional<AqiResponse> fetchData(String target) {
        String targetUrl = String.format("%s/%s/?token=%s", url, target, token);

        try {
            return Optional.ofNullable(restTemplate.getForObject(targetUrl, AqiResponse.class));
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }
}
