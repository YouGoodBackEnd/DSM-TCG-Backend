package com.project.tcg.domain.chest.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class UnopenedChestException extends BusinessException {
    public static final BusinessException EXCEPTION = new UnopenedChestException();
    private UnopenedChestException(){
        super(ErrorCode.UNOPENED_CHEST_EXCEPTION);
    }
}