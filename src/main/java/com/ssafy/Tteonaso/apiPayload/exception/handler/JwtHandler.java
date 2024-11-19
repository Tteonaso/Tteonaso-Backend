package com.ssafy.Tteonaso.apiPayload.exception.handler;

import com.ssafy.Tteonaso.apiPayload.code.BaseErrorCode;
import com.ssafy.Tteonaso.apiPayload.exception.GeneralException;

public class JwtHandler extends GeneralException {
    public JwtHandler(BaseErrorCode code) {
        super(code);
    }
}
