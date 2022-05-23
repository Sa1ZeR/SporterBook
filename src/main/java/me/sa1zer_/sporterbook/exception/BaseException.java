package me.sa1zer_.sporterbook.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * An extensible base exception with an error message.
 *
 * @see RuntimeException
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public abstract class BaseException extends RuntimeException {

    protected String message;
}
