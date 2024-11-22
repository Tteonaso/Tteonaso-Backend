package com.ssafy.Tteonaso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.create()
                .wiretap(true)
                .compress(true)
                .responseTimeout(Duration.ofSeconds(30)); // 응답 대기 시간 30초로 설정

        return builder
                .baseUrl("https://api.openai.com")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
