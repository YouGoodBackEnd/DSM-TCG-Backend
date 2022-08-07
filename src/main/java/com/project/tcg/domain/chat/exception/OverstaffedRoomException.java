package com.project.tcg.domain.chat.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class OverstaffedRoomException extends BusinessException {
    public static final BusinessException EXCEPTION = new OverstaffedRoomException();
    private OverstaffedRoomException(){
        super(ErrorCode.OVERSTAFFED_ROOM);
    }
}