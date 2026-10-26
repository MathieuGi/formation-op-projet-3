package com.girardon.chatop.exception;

public class EntityNotFoundException extends RuntimeException {
    private String message = "";

    public EntityNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
