package me.sa1zer_.sporterbook.exception;

import lombok.Getter;

@Getter
public class LogNotFoundException extends BaseException{
    public LogNotFoundException(String message) {
        super(message);
    }
}
