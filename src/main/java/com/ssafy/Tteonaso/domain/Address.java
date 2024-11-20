package com.ssafy.Tteonaso.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
    name = "address",
    indexes = {
        @Index(name = "idx_sido_name", columnList = "sidoName"),
        @Index(name = "idx_gugun_name", columnList = "gugunName")
    }
)
public class Address {
    @Id
    String dongCode;

    String sidoName;

    String gugunName;

    String dongName;

}
