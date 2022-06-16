package com.project.dcb.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    INCORRECT_TOKEN(400, "Incorrect Token"), //Bad request
    EXPIRED_ACCESS_TOKEN(401, "Expired Access Token"), //Unauthorized
    EXPIRED_REFRESH_TOKEN(401, "Expired Refresh Token"),
    INVALID_TOKEN(401, "Invalid Token"),

    USER_NOT_FOUND(404,"User Nor Found"), //Not found
    USER_ALREADY_EXISTS(409, "User Already Exists"), //Conflict
    INVALID_INFORMATION(401, "Invalid Information"),
    PASSWORD_MISMATCH(404, "Password Mismatch"),

    BOARD_NOT_EXIST(404, "Board Not Exist"),

    WRONG_APPROACH(404, "Wrong Approach");


    private final int statusCode;
    private final String message;
}