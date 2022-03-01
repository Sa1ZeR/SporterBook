package me.sa1zer_.sporterbook.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SuccessLoginResponse {

    private String token;
}
