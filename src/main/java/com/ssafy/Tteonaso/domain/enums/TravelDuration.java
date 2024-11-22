package com.ssafy.Tteonaso.domain.enums;

import lombok.Getter;

@Getter
public enum TravelDuration {
    SAME_DAY_TRIP("당일치기"),
    ONE_NIGHT_TWO_DAYS("1박 2일"),
    TWO_NIGHTS_THREE_DAYS("2박 3일"),
    THREE_NIGHTS_FOUR_DAYS("3박 4일"),
    FOUR_NIGHTS_FIVE_DAYS("4박 5일"),
    FIVE_NIGHTS_SIX_DAYS("5박 6일");

    private final String description;

    TravelDuration(String description) {
        this.description = description;
    }
}
