package me.sa1zer_.sporterbook.payload.response;

import lombok.Data;

@Data
public class MessageResponse {

    private int status;
    private String message;

    public MessageResponse(String message) {
        this.message = message;
        status = 200;
    }

    public MessageResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
