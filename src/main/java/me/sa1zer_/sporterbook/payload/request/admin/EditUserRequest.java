package me.sa1zer_.sporterbook.payload.request.admin;

import lombok.Data;
import me.sa1zer_.sporterbook.annotation.Email;
import me.sa1zer_.sporterbook.annotation.Login;
import me.sa1zer_.sporterbook.annotation.Phone;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import me.sa1zer_.sporterbook.domain.model.enums.Sex;
import me.sa1zer_.sporterbook.payload.handler.RequestHandleable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Data about edited user information.
 */
@Data
public class EditUserRequest implements RequestHandleable {

    @NotBlank
    private Long id;

    @Size(min = 3, max = 48, message = "Неверный формат для имени")
    private String firstNmae;

    @Size(min = 3, max = 48, message = "Неверный формат для фамилии")
    private String lastName;

    @Size(min = 3, max = 48, message = "Неверный формат для отчества")
    private String patronymic;

    @Email
    private String email;

    @Login
    private String login;

    @Phone
    private String phone;

    @Size(min = 8, max = 32, message = "Пароль должен быть от 8 до 32 символов")
    private String password;

    @NotNull(message = "Пол не может быть пустым")
    private Sex sex;

    private Set<Role> roles;
}
