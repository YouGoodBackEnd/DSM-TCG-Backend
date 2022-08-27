package com.project.tcg.domain.chat.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class FulledRoomException extends BusinessException {
    public static final BusinessException EXCEPTION = new FulledRoomException();
    private FulledRoomException(){
        super(ErrorCode.FULLED_ROOM);
    }
}