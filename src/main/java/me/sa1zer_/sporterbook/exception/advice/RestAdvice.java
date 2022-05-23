package me.sa1zer_.sporterbook.exception.advice;

import me.sa1zer_.sporterbook.exception.BaseException;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Additional processing of REST exceptions.
 */
@RestControllerAdvice
public class RestAdvice {

    /**
     * Handling REST errors.
     * @param baseException registers for all classes that inherit from {@link BaseException}
     * @return Sends error code 400 after throw exceptions with an error message
     */
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<?> handleRestErrors(BaseException baseException) {
        return ResponseEntity.badRequest().body(new MessageResponse(400, baseException.getMessage()));
    }
}
