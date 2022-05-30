package me.sa1zer_.sporterbook.payload.request.admin;

import lombok.Data;
import me.sa1zer_.sporterbook.annotation.Email;
import me.sa1zer_.sporterbook.annotation.Login;
import me.sa1zer_.sporterbook.annotation.Phone;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import me.sa1zer_.sporterbook.domain.model.enums.Sex;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Data about edited user information.
 */
@Data
public class EditUserRequest {

    @NotNull(message = "id не может быть пустым")
    private Long id;

    @Size(min = 3, max = 48, message = "Неверный формат для имени")
    @NotNull(message = "имя не может быть пустым")
    private String firstName;

    @Size(min = 3, max = 48, message = "Неверный формат для фамилии")
    @NotNull(message = "фамилия не может быть пустым")
    private String lastName;

    @Size(min = 3, max = 48, message = "Неверный формат для отчества")
    private String patronymic;

    @Email
    @NotNull(message = "почта не может быть пустой")
    private String email;

    @Login
    @NotNull(message = "логин не может быть пустым")
    private String login;

    @Phone
    private String phone;

    @Size(min = 8, max = 32, message = "Пароль должен быть от 8 до 32 символов")
    private String password;

    @NotNull(message = "Пол не может быть пустым")
    private Sex sex;

    private Set<Role> roles;
}
