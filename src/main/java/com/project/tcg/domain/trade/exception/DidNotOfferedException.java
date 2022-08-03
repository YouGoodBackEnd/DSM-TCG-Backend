package com.project.tcg.domain.trade.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class DidNotOfferedException extends BusinessException {
    public static final BusinessException EXCEPTION = new DidNotOfferedException();
    private DidNotOfferedException(){
        super(ErrorCode.DID_NOT_OFFERED);
    }
}