package me.sa1zer_.sporterbook.payload.request;

import lombok.Data;
import me.sa1zer_.sporterbook.annotation.Email;
import me.sa1zer_.sporterbook.annotation.Login;
import me.sa1zer_.sporterbook.annotation.Phone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

    @NotBlank(message = "Имя не может быть мустым")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть мустым")
    private String latName;

    @NotBlank(message = "Отчество не может быть мустым")
    private String patronymic;

    @Login
    @NotBlank(message = "Логин не может быть пустым")
    private String login;

    @Email
    @NotBlank(message = "email не может быть пустым")
    private String email;

    @Size(min = 8, max = 32, message = "Пароль должен быть от 8 до 32 символов")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;

    @NotBlank(message = "Подтверждающий пароль не может быть пустым")
    private String passwordConfirm;

    @NotBlank(message = "Телефон не может быть пустым")
    @Phone(message = "Неверно указан номер телефона")
    private String phone;

    private boolean sex;

}
