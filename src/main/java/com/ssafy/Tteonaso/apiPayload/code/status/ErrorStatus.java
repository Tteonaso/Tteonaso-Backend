package com.ssafy.Tteonaso.apiPayload.code.status;

import com.ssafy.Tteonaso.apiPayload.code.BaseErrorCode;
import com.ssafy.Tteonaso.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON5001", "서버 에러, 관리자에게 문의 바랍니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON5002", "잘못된 접근입니다."),
    ATHENTICATION_NOT_FOUND(HttpStatus.FORBIDDEN, "JWT4001", "인증 정보를 찾을 수 없습니다."),
    ACCESSTOKEN_INVALID(HttpStatus.FORBIDDEN, "JWT4002", "유효한 토큰이 아닙니다"),
    ALREADY_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "MEMBER4001", "이미 존재하는 멤버입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
