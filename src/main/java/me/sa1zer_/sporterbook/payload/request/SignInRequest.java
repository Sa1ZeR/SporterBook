package me.sa1zer_.sporterbook.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The data model obtained from user sign in.
 */
@Data
public class SignInRequest {

    @Size(min = 3, max = 16, message = "Логин должен быть от 3 до 24 символов")
    @NotBlank(message = "Логин или email не может быть пустым")
    private String login;

    @Size(min = 8, max = 32, message = "Пароль должен быть от 8 до 32 символов")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}
