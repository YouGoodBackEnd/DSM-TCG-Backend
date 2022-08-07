package com.project.tcg.domain.trade.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class BadOfferException extends BusinessException {
    public static final BusinessException EXCEPTION = new BadOfferException();
    private BadOfferException(){
        super(ErrorCode.BAD_OFFER);
    }
}