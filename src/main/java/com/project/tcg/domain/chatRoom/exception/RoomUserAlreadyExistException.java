package com.project.tcg.domain.chatRoom.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class RoomUserAlreadyExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new RoomUserAlreadyExistException();
    private RoomUserAlreadyExistException(){
        super(ErrorCode.ROOM_USER_ALREADY_EXIST);
    }
}