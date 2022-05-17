package me.sa1zer_.sporterbook.payload.handler;

import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface RequestHandler<T> {

    ResponseEntity<?> handle(T request);

    ResponseEntity<?> handleByPrinciple(T request, Principal principal);
}
