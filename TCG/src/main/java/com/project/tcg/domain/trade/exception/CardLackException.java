package com.project.tcg.domain.trade.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class CardLackException extends BusinessException {
    public static final BusinessException EXCEPTION = new CardLackException();
    private CardLackException(){
        super(ErrorCode.CARD_LACK);
    }
}