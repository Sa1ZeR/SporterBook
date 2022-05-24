package me.sa1zer_.sporterbook.payload.request.admin;

import lombok.Data;

@Data
public class AddStudentToSectionRequest {

    private Long section;
    private Long student;
}
