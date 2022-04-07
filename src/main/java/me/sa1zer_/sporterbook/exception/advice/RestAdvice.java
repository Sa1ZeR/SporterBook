package me.sa1zer_.sporterbook.exception.advice;

import me.sa1zer_.sporterbook.exception.BaseException;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestAdvice {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<?> handleRestErrors(BaseException baseException) {
        return ResponseEntity.badRequest().body(new MessageResponse(400, baseException.getMessage()));
    }
}
