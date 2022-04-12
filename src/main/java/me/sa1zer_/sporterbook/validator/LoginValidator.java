package me.sa1zer_.sporterbook.validator;

import me.sa1zer_.sporterbook.annotation.Login;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class LoginValidator implements ConstraintValidator<Login, String> {

    private static Pattern pattern = Pattern.compile("^[\\w]{3,16}");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value) && pattern.matcher(value).matches();
    }
}
