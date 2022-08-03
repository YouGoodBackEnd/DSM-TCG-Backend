package com.project.tcg.global.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class SaveImageFailedException extends BusinessException {
    public static final BusinessException EXCEPTION = new SaveImageFailedException();
    private SaveImageFailedException(){
        super(ErrorCode.SAVE_IMAGE_FAILED);
    }
}