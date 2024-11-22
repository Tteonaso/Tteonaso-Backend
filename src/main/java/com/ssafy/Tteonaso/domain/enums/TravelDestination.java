package com.ssafy.Tteonaso.domain.enums;

import lombok.Getter;

@Getter
public enum TravelDestination {
    SEOUL("서울"),
    GAPYEONG("가평"),
    GANGNEUNG_SOKCHO("강릉/속초"),
    GYEONGJU("경주"),
    BUSAN("부산"),
    YEOSU("여수"),
    INCHEON("인천"),
    JEONJU("전주"),
    JEJU("제주"),
    CHUNCHEON("춘천"),
    TAEAN("태안"),
    TONGYEONG("통영"),
    POHANG("포항");

    private final String description;

    TravelDestination(String description) {
        this.description = description;
    }
}