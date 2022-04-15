package me.sa1zer_.sporterbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TimeTableInfoNotFoundException extends BaseException {
    public TimeTableInfoNotFoundException(String s) {
        super(s);
    }
}
