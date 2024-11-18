package com.ssafy.Tteonaso.apiPayload.exception;

import com.ssafy.Tteonaso.apiPayload.code.BaseErrorCode;
import com.ssafy.Tteonaso.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException {
    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}