package com.ssafy.Tteonaso.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3UploaderService {
    @Value("${S3_BUCKET}")
    private String bucket;

    private final S3Client s3Client;

    public void uploadLogFileToS3(String filePath, String s3Key) {
        try {
            File logFile = new File(filePath);
            if (!logFile.exists()) {
                log.warn("로그 파일이 존재하지 않습니다: {}", filePath);
                return;
            }

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(s3Key)
                            .contentType("text/plain; charset=UTF-8")
                            .build(),
                    Paths.get(filePath)
            );

            log.info("로그 파일이 S3에 업로드되었습니다: {}/{}", bucket, s3Key);
        } catch (Exception e) {
            log.error("S3 업로드 중 에러 발생: {}", e.getMessage(), e);
        }
    }
}
