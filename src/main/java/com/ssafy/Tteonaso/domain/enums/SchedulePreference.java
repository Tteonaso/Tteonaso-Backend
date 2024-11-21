package com.ssafy.Tteonaso.domain.enums;

import lombok.Getter;

@Getter
public enum SchedulePreference {
    RELAXED_SCHEDULE("널널한 일정"),
    DENSE_SCHEDULE("빼곡한 일정");

    private final String description;

    SchedulePreference(String description) {
        this.description = description;
    }
}