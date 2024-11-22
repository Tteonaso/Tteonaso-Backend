package com.ssafy.Tteonaso.domain.enums;

import lombok.Getter;

@Getter
public enum CompanionType {
    ALONE("혼자"),
    WITH_FRIEND("친구와"),
    WITH_PARTNER("연인과"),
    WITH_CHILDREN("아이와"),
    WITH_PARENTS("부모님과"),
    OTHER("기타");

    private final String description;

    CompanionType(String description) {
        this.description = description;
    }
}
