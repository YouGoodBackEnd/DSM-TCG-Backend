package com.project.tcg.domain.user.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class PasswordNotValidException extends BusinessException {
    public static final BusinessException EXCEPTION = new PasswordNotValidException();
    private PasswordNotValidException(){
        super(ErrorCode.PASSWORD_NOT_VALID);
    }
}