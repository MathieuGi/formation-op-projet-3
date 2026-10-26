package com.girardon.chatop.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final String message;

    public EntityNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
