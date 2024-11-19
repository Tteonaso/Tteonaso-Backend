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
public class Dong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long dongId;

    String dongName;

    @OneToMany(mappedBy = "dong", cascade = CascadeType.ALL)
    List<Address> addressList;
}
