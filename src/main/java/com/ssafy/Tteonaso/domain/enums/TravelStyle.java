package com.ssafy.Tteonaso.domain.enums;

import lombok.Getter;

@Getter
public enum TravelStyle {
    ACTIVITY_ADVENTURE("체험 및 액티비티"),
    SNS_HOT_PLACE("SNS 핫플레이스"),
    WITH_NATURE("자연과 함께"),
    FAMOUS_TOURIST_SPOTS("유명 관광지 방문"),
    RELAXING_HEALING("여유롭게 힐링"),
    CULTURE_ART_HISTORY("문화 및 예술 역사"),
    FEEL_THE_PLACE("여행지 느낌 물씬"),
    SHOPPING("쇼핑을 열정적으로"),
    FOOD_OVER_SIGHTSEEING("관광보다 먹방");

    private final String description;

    TravelStyle(String description) {
        this.description = description;
    }
}
