package com.ssafy.Tteonaso.scheduler;

import com.ssafy.Tteonaso.service.S3UploaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogUploadScheduler {

    private final S3UploaderService s3UploaderService;

    /**
     * 1시간마다 로그 파일을 S3에 업로드
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void uploadLogToS3() {
        // 현재 시간 기준 1시간 전
        LocalDateTime oneHourAgo = LocalDateTime.now(ZoneId.of("Asia/Seoul")).minusHours(1);
        String logDate = oneHourAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 로그 파일의 날짜 추출
        String logFilePath = String.format("logs/application.%s.log", logDate);
        String s3Key = String.format("logs/%s/application.%s.log", logDate, logDate);

        log.info("S3 업로드를 시작합니다. 파일 경로: {}, S3 키: {}", logFilePath, s3Key);

        s3UploaderService.uploadLogFileToS3(logFilePath, s3Key);
    }
}
