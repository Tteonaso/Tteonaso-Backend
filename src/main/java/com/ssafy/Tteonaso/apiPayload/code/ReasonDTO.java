package com.ssafy.Tteonaso.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ReasonDTO {
    private final HttpStatus httpStatus;
    private final boolean isSuccess;
    private final String code;
    private String message;

    public boolean getIsSuccess() {
        return this.isSuccess;
    }
}
