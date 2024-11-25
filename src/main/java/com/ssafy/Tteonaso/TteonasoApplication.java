package com.ssafy.Tteonaso;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class TteonasoApplication {
	private final Environment environment;

	public static void main(String[] args) {
		init();
		SpringApplication.run(TteonasoApplication.class, args);
	}

	private static void init() {
		log.info("Spring Server TimeZone : Asia/Seoul");
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
}
