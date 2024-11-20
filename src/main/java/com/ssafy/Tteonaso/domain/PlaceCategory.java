package com.ssafy.Tteonaso.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
    name = "place_category",
    indexes = {
        @Index(name = "idx_category_code", columnList = "categoryCode"),
    }
)
public class PlaceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryCode;

    private String categoryName;
}
