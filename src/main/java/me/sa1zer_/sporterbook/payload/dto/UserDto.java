package me.sa1zer_.sporterbook.payload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import me.sa1zer_.sporterbook.domain.model.enums.Sex;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserDto implements IUserDto {

    private Long id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phone;
    private Sex sex;
    private Set<Role> roles;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDateTime birth;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDateTime created;
}
