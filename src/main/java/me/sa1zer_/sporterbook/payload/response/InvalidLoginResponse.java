package me.sa1zer_.sporterbook.payload.response;

import lombok.Data;

@Data
public class InvalidLoginResponse {

    private String message;

    public InvalidLoginResponse() {
        message = "Неверное имя пользователя или пароль";
    }
}
