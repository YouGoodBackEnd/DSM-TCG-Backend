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

    USER_ALREADY_EXIST(409, "USER-409-1", "User Already Exist"),


    REFRESH_TOKEN_NOT_FOUND(404, "AUTH-404-1", "Refresh Token Not Found");

    private final Integer status;
    private final String code;
    private final String message;
}