package me.sa1zer_.sporterbook.payload.handler.handle.admin.user;

import me.sa1zer_.sporterbook.domain.LogConstants;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.enums.LogType;
import me.sa1zer_.sporterbook.payload.request.admin.EditUserRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.LogService;
import me.sa1zer_.sporterbook.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class EditRequestHandler {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final LogService logService;

    public EditRequestHandler(UserService userService, BCryptPasswordEncoder passwordEncoder,
                              LogService logService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.logService = logService;
    }
}
