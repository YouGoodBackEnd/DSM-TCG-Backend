package com.project.tcg.domain.trade.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class CoinLackException extends BusinessException {
    public static final BusinessException EXCEPTION = new CoinLackException();
    private CoinLackException(){
        super(ErrorCode.COIN_LACK);
    }
}