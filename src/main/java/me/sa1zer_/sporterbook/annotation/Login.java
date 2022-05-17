package me.sa1zer_.sporterbook.annotation;

import me.sa1zer_.sporterbook.validator.LoginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated field will be checked
 * for the correct format of the login.
 *
 * @see LoginValidator
 */
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LoginValidator.class)
public @interface Login {

    /**
     * Specify your message or keep it by default.
     * @return message about incorrect login input
     */
    String message() default "Неверный формат для логина!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
