package com.ssafy.Tteonaso.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MattermostLoggingService implements LoggingService{
    private final WebClient webClient;
    @Value("${MM_WEBHOOK_URL}")
    private String webhookUrl;

    @Override
    public void sendLog(String message) {
        var payload = Map.of("text", message);

        webClient.post()
                .uri(webhookUrl)
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(aVoid -> log.info("Mattermost 로그 전송 성공: {}", message))
                .doOnError(e -> log.error("Mattermost 로그 전송 실패: {}", e.getMessage()))
                .subscribe(); // 비동기 호출 실행
    }
}
