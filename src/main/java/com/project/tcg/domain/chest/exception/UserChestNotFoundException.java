package com.project.tcg.domain.chest.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class UserChestNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserChestNotFoundException();
    private UserChestNotFoundException(){
        super(ErrorCode.USER_CHEST_NOT_FOUND);
    }
}