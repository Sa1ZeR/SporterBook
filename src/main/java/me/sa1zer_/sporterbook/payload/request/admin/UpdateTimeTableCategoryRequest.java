package me.sa1zer_.sporterbook.payload.request.admin;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateTimeTableCategoryRequest {

    private Long id;
    @NotBlank
    private String name;
}
