package com.ssafy.Tteonaso.domain;

import com.ssafy.Tteonaso.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Gugun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long gugunId;

    String gugunName;

    @OneToMany(mappedBy = "gugun", cascade = CascadeType.ALL)
    List<Address> addressList;

}
