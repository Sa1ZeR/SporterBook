package me.sa1zer_.sporterbook.payload.request.admin;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class UpdateRoomRequest {

    private Long id;
    @Positive
    private int num;
}
