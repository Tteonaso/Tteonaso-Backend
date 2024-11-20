package com.ssafy.Tteonaso.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address {
    @Id
    String dongCode;

    String sidoName;

    String gugunName;

    String dongName;

}
