package me.sa1zer_.sporterbook.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignInRequest {

    @NotBlank(message = "Логин или email не может быть пустым")
    private String login;

    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}
