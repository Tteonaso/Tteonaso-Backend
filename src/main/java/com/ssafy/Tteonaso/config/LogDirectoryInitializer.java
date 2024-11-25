package com.ssafy.Tteonaso.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
public class LogDirectoryInitializer {
    @PostConstruct
    public void init() {
        File logDir = new File("logs");
        if (!logDir.exists()) {
            boolean created = logDir.mkdirs();
            if (created) {
                log.info("Log directory created: " + logDir.getAbsolutePath());
            }
        }
    }
}
