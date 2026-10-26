package com.girardon.chatop.exception;

import lombok.Getter;

@Getter
public class UserExistsException extends RuntimeException {
    private final String message;

    public UserExistsException(String msg) {
        super(msg);
        this.message = msg;
    }

}
