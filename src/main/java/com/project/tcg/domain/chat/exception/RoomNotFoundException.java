package com.project.tcg.domain.chat.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class RoomNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new RoomNotFoundException();
    private RoomNotFoundException(){
        super(ErrorCode.ROOM_NOT_FOUND);
    }
}