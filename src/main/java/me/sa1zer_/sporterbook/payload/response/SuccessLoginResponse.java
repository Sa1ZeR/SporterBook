package me.sa1zer_.sporterbook.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.sa1zer_.sporterbook.payload.dto.UserDto;

@AllArgsConstructor
@Data
public class SuccessLoginResponse {

    private UserDto user;
    private String token;
}
