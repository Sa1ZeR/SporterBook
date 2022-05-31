package me.sa1zer_.sporterbook.payload.request.admin;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateTimeTableInfoRequest {

    private Long id;

    @NotNull(message = "id расписания не может быть пустым")
    private Long timetable;
    @NotNull(message = "секции зала не может быть пустым")
    private Long section;
    @NotNull(message = "id зала не может быть пустым")
    private Long room;
    @NotNull(message = "id категории не может быть пустым")
    private Long category;
}
