package me.sa1zer_.sporterbook.payload.request.admin;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AcceptStudentRequest {

    @NotNull(message = "id секции не может быть пустым")
    private Long id;
    @NotNull(message = "id пользователя не может быть пустым")
    private Long student;
}
