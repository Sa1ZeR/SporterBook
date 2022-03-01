package me.sa1zer_.sporterbook.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    public static ResponseEntity<Object> validBindingResult(BindingResult result) {
        if(result.hasErrors() && !CollectionUtils.isEmpty(result.getAllErrors())) {
            Map<String, String> errors = new HashMap<>();

            for(ObjectError error : result.getAllErrors()) {
                errors.put(error.getCode(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
