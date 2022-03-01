package me.sa1zer_.sporterbook.annotation;

import me.sa1zer_.sporterbook.validator.LoginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LoginValidator.class)
public @interface Login {

    String message() default "Неверный формат для логина!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
