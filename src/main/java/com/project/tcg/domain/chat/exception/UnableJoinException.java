package com.project.tcg.domain.chat.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class UnableJoinException extends BusinessException {
    public static final BusinessException EXCEPTION = new UnableJoinException();
    private UnableJoinException(){
        super(ErrorCode.UNABLE_PARTICIPATE);
    }
}