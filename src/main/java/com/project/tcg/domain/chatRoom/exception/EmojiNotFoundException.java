package com.project.tcg.domain.chatRoom.exception;

import com.project.tcg.global.error.exception.BusinessException;
import com.project.tcg.global.error.exception.ErrorCode;

public class EmojiNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new EmojiNotFoundException();
    private EmojiNotFoundException(){
        super(ErrorCode.EMOJI_NOT_FOUND);
    }
}