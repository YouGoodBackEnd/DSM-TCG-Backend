package com.project.tcg.domain.trade.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class SocketClientNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new SocketClientNotFoundException();
    private SocketClientNotFoundException(){
        super(ErrorCode.SOCKET_CLIENT_NOT_FOUND);
    }
}