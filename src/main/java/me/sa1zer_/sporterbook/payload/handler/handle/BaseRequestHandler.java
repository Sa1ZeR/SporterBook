package me.sa1zer_.sporterbook.payload.handler.handle;

import me.sa1zer_.sporterbook.payload.handler.RequestHandleable;
import me.sa1zer_.sporterbook.payload.handler.RequestHandler;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public class BaseRequestHandler implements RequestHandler {
    @Override
    public ResponseEntity<?> handle(Object request) {
        return ResponseEntity.badRequest()
                .body(new MessageResponse("Request Handler not found!"));
    }

    @Override
    public ResponseEntity<?> handleByPrinciple(Object request, Principal principal) {
        return ResponseEntity.badRequest()
                .body(new MessageResponse("Request Handler not found!"));
    }
}
