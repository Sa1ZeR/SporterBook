package me.sa1zer_.sporterbook.payload.request.admin;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddTrainerToSectionRequest {

    @NotNull
    private Long section;
    @NotNull
    private Long trainer;
}
