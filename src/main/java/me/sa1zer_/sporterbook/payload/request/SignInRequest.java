package me.sa1zer_.sporterbook.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignInRequest {

    @NotBlank(message = "Логин или email не может быть пустым")
    private String login;

    @Size(min = 8, max = 32, message = "Пароль должен быть от 8 до 32 символов")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}
