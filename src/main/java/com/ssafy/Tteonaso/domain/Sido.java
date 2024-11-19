package com.ssafy.Tteonaso.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "sido", cascade = CascadeType.ALL)
    List<Address> addressList;

}
