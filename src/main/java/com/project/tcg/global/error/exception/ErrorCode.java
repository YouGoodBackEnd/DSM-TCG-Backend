package com.project.tcg.global.error.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    SAVE_IMAGE_FAILED(400, "COMMON-400-1", "Save Image Failed"),

    EXPIRED_TOKEN(401,"COMMON401-1", "Expired Token" ),
    INVALID_TOKEN(401,"COMMON401-2","Invalid Token"),

    IMAGE_VALUE_NOT_FOUND(400, "COMMON-404-1", "Image Value Not Found"),

    PASSWORD_NOT_VALID(401, "USER-401-1", "Password Not Valid"),
    USER_NOT_FOUND(404, "USER-404-2", "User Not Found" ),
    USER_ALREADY_EXIST(409, "USER-409-1", "User Already Exist"),

    CARD_NOT_FOUND(404, "CARD-404-1", "Card Not Found"),

    ROOM_NOT_FOUND(404, "ROOM-404-1", "Room Not Found"),
    OVERSTAFFED_ROOM(409, "ROOM-409-1", "Overstaffed Room"),
    ROOM_USER_ALREADY_EXIST(409, "ROOM-409-2", "Already joining another room"),

    EMOJI_NOT_FOUND(404, "CHAT-404-1", "Emoji Not Found"),

    DID_NOT_OFFERED(400, "TRADE-400-1", "Did Not Offered"),
    COIN_LACK(409, "TRADE-409-2", "Coin Lack"),
    CARD_LACK(409, "TRADE-409-3", "Card Lack"),
    ALREADY_ACCEPTED(409, "TRADE-409-4", "Already Accepted"),

    REFRESH_TOKEN_NOT_FOUND(404, "AUTH-404-1", "Refresh Token Not Found"),

    USER_CHEST_NOT_FOUND(404, "CHEST-404-1", "User Chest Not Found"),
    UNOPENED_CHEST_EXCEPTION(409, "CHEST-409-1", "Unopened Chest"),

    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal Server Error");

    private final Integer status;
    private final String code;
    private final String message;
}