package me.sa1zer_.sporterbook.annotation;

import me.sa1zer_.sporterbook.validator.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated field will be checked
 * for the correct format of the email.
 *
 * @see EmailValidator
 */
@Target({ElementType.FIELD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

    /**
     * Specify your message or keep it by default.
     * @return message about incorrect email input
     */
    String message() default "Указанный email невалидный";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
