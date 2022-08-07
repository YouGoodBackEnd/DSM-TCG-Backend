package com.project.tcg.domain.trade.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class OfferImpossibleException extends BusinessException {
    public static final BusinessException EXCEPTION = new OfferImpossibleException();
    private OfferImpossibleException() {
        super(ErrorCode.OFFER_IMPOSSIBLE);
    }
}