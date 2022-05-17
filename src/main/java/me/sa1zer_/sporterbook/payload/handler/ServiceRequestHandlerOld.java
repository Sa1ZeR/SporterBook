package me.sa1zer_.sporterbook.payload.handler;

import org.springframework.http.ResponseEntity;

import java.security.Principal;

@Deprecated
public interface ServiceRequestHandlerOld {

    ResponseEntity<?> handleRequest(Object request);

    ResponseEntity<?> handleRequestByPrincipal(Object request, Principal principal);
}
