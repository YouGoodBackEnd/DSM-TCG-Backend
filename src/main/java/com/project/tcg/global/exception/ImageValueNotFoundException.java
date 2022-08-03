package com.project.tcg.global.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class ImageValueNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new ImageValueNotFoundException();
    private ImageValueNotFoundException(){
        super(ErrorCode.IMAGE_VALUE_NOT_FOUND);
    }
}