package com.ssafy.Tteonaso.apiPayload.exception.handler;

import com.ssafy.Tteonaso.apiPayload.code.BaseErrorCode;
import com.ssafy.Tteonaso.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
