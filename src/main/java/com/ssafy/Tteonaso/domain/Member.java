package com.ssafy.Tteonaso.domain;

import com.ssafy.Tteonaso.domain.enums.Gender;
import com.ssafy.Tteonaso.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    Long memberId;
    String name;
    String password;
    //Address address;
    Gender gender;
    String email;
    String phone;
    String profileImage;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    Status status;
}
