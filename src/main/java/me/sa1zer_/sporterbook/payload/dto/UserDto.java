package me.sa1zer_.sporterbook.payload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import me.sa1zer_.sporterbook.domain.Sex;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phone;
    private Sex sex;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDateTime birth;
}
