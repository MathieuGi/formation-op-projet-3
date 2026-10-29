package com.girardon.chatop.exception;

import lombok.Getter;

@Getter
public class FileException extends RuntimeException {
    private final String message;

    public FileException(String msg) {
        super(msg);
        this.message = msg;
    }
}
