package com.project.tcg.domain.trade.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class CardNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new CardNotFoundException();
    private CardNotFoundException(){
        super(ErrorCode.CARD_NOT_FOUND);
    }
}