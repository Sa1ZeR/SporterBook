package me.sa1zer_.sporterbook.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public abstract class BaseException extends RuntimeException {

    protected String message;
}
