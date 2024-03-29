package com.project.tcg.global.error.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    SAVE_IMAGE_FAILED(400, "COMMON-400-1", "Save Image Failed"),

    EXPIRED_TOKEN(401,"AUTH-401-1", "Expired Token" ),
    INVALID_TOKEN(401,"AUTH-401-2","Invalid Token"),
    REFRESH_TOKEN_NOT_FOUND(404, "AUTH-404-1", "Refresh Token Not Found"),

    IMAGE_VALUE_NOT_FOUND(400, "COMMON-404-1", "Image Value Not Found"),

    PASSWORD_NOT_VALID(401, "USER-401-1", "Password Not Valid"),
    USER_NOT_FOUND(404, "USER-404-2", "User Not Found" ),
    USER_ALREADY_EXIST(409, "USER-409-1", "User Already Exist"),

    EMOJI_NOT_FOUND(404, "CHAT-404-1", "Emoji Not Found"),
    ROOM_NOT_FOUND(404, "ROOM-404-1", "Room Not Found"),
    FULLED_ROOM(409, "ROOM-409-1", "Overstaffed Room"),
    UNABLE_PARTICIPATE(409, "ROOM-409-2", "Already joining another room"),

    BAD_OFFER(400, "TRADE-400-1", "Bad Offer"),
    CARD_NOT_FOUND(404, "TRADE-404-1", "Card Not Found"),
    COIN_LACK(409, "TRADE-409-1", "Coin Lack"),
    CARD_LACK(409, "TRADE-409-2", "Card Lack"),
    OFFER_IMPOSSIBLE(409, "TRADE-409-3", "Offer Impossible"),
    ACCEPT_IMPOSSIBLE(409, "TRADE-409-4", "Accept Impossible"),

    USER_CHEST_NOT_FOUND(404, "CHEST-404-1", "User Chest Not Found"),
    UNOPENED_CHEST_EXCEPTION(409, "CHEST-409-1", "Unopened Chest"),

    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal Server Error");

    private final Integer status;
    private final String code;
    private final String message;
}