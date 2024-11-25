package com.ssafy.Tteonaso.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import com.ssafy.Tteonaso.logging.MattermostAppender;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class LogbackConfig {

    @Value("${MM_WEBHOOK_URL}") // 환경 변수 또는 설정 파일에서 가져오기
    private String webhookUrl;

    @PostConstruct
    public void configureLogback() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        // Mattermost Custom Appender 생성
        MattermostAppender mattermostAppender = new MattermostAppender();
        mattermostAppender.setWebhookUrl(webhookUrl);
        mattermostAppender.setContext(context);
        mattermostAppender.start();

        // 파일 로그 Appender 생성
        RollingFileAppender fileAppender = new RollingFileAppender();
        fileAppender.setContext(context);
        fileAppender.setName("FILE");

        // 로그 파일 롤링 정책 설정 (일별 파일 생성)
        TimeBasedRollingPolicy rollingPolicy = new TimeBasedRollingPolicy();
        rollingPolicy.setContext(context);
        rollingPolicy.setFileNamePattern("logs/application.%d{yyyy-MM-dd, Asia/Seoul}.log");
        rollingPolicy.setMaxHistory(7); // 최대 7일간 로그 파일 유지
        rollingPolicy.setParent(fileAppender);
        rollingPolicy.start();

        fileAppender.setRollingPolicy(rollingPolicy);

        // 로그 패턴 설정
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss Z} [%thread] %-5level %logger{36} - %msg%n");
        encoder.setCharset(StandardCharsets.UTF_8);
        encoder.start();

        fileAppender.setEncoder(encoder);
        fileAppender.start();

        // Root Logger에 Appenders 추가
        Logger rootLogger = context.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.addAppender(mattermostAppender);
        rootLogger.addAppender(fileAppender);
        // 로그 레벨 설정 (WARN 이상)
        rootLogger.setLevel(Level.WARN);

        // 초기 로그 기록
        Logger logger = (Logger) LoggerFactory.getLogger(LogbackConfig.class);
        logger.info("Application started. Initializing logs...");
    }
}
