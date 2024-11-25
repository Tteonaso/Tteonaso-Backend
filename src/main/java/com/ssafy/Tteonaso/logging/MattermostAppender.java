package com.ssafy.Tteonaso.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.Setter;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

public class MattermostAppender extends AppenderBase<ILoggingEvent> {
    @Setter
    private String webhookUrl;
    private WebClient webClient;

    @Override
    public void start() {
        if (webhookUrl == null) {
            addError("Webhook URL is not configured for MattermostAppender");
            return;
        }
        webClient = WebClient.create();
        super.start();
    }

    @Override
    protected void append(ILoggingEvent event) {
        try {
            String message = String.format("**[%s]** %s - %s",
                    event.getLevel(),
                    event.getLoggerName(),
                    event.getFormattedMessage());

            sendLogToMattermost(message);
        } catch (Exception e) {
            addError("Mattermost 로깅 전송 실패 : ", e);
        }
    }

    private void sendLogToMattermost(String message) {
        var payload = Map.of("text", message);

        webClient.post()
                .uri(webhookUrl)
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(error -> addError("Failed to send log to Mattermost", error))
                .subscribe();
    }
}
