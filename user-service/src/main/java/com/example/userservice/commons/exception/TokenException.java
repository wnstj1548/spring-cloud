package com.example.userservice.commons.exception;

import com.example.userservice.commons.exception.payload.ErrorStatus;

public class TokenException extends ApplicationException {

    public TokenException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
