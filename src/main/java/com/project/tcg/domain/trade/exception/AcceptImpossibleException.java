package com.project.tcg.domain.trade.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class AcceptImpossibleException extends BusinessException {
    public static final BusinessException EXCEPTION = new AcceptImpossibleException();
    private AcceptImpossibleException(){
        super(ErrorCode.ACCEPT_IMPOSSIBLE);
    }
}