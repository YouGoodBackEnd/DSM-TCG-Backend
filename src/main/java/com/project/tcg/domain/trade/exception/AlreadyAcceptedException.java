package com.project.tcg.domain.trade.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class AlreadyAcceptedException extends BusinessException {
    public static final BusinessException EXCEPTION = new AlreadyAcceptedException();
    private AlreadyAcceptedException() {
        super(ErrorCode.ALREADY_ACCEPTED);
    }
}