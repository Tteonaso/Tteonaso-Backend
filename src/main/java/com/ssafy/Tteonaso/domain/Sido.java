package com.ssafy.Tteonaso.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Sido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long sidoId;

    String sidoName;

}
