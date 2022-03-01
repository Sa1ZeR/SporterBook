package me.sa1zer_.sporterbook.validator;

import me.sa1zer_.sporterbook.annotation.Phone;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    //private static final Pattern PATTERN = Pattern.compile("^\\d{11}");
    private static final Pattern PATTERN = Pattern.compile("(\\d{11})|(^\\+\\d{11})");

    //89024823663

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value) && PATTERN.matcher(value).matches();
    }
}
