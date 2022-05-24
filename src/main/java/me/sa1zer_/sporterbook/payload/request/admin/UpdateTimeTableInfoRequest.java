package me.sa1zer_.sporterbook.payload.request.admin;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateTimeTableInfoRequest {

    private Long id;

    @NotNull
    private Long timetable;
    @NotNull
    private Long section;
    @NotNull
    private Long room;
    @NotNull
    private Long category;
}
