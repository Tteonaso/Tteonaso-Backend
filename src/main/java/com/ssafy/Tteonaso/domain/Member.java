package com.ssafy.Tteonaso.domain;

import com.ssafy.Tteonaso.domain.common.BaseEntity;
import com.ssafy.Tteonaso.domain.enums.Gender;
import com.ssafy.Tteonaso.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long memberId;

    String name;

    String password;

    //Address address;

    @Enumerated(EnumType.STRING)
    Gender gender;

    String email;

    String phone;

    String profileImage;

    @Enumerated(EnumType.STRING)
    Status status;
}
