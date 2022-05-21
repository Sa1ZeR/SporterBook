package me.sa1zer_.sporterbook.payload.handler.handle.admin.user;

import me.sa1zer_.sporterbook.domain.LogConstants;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.enums.LogType;
import me.sa1zer_.sporterbook.payload.handler.RequestHandler;
import me.sa1zer_.sporterbook.payload.request.admin.EditUserRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.LogService;
import me.sa1zer_.sporterbook.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class EditRequestHandler implements RequestHandler<EditUserRequest> {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final LogService logService;

    public EditRequestHandler(UserService userService, BCryptPasswordEncoder passwordEncoder,
                              LogService logService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.logService = logService;
    }

    @Override
    public ResponseEntity<?> handle(EditUserRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> handleByPrinciple(EditUserRequest request, Principal principal) {
        User user = userService.findById(request.getId());

        User admin = userService.findByPrincipal(principal);

        user.setFistName(request.getFirstNmae());
        user.setLastName(request.getLastName());
        user.setPatronymic(request.getPatronymic());
        user.setLogin(request.getLogin());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setSex(request.getSex());
        user.setRoles(request.getRoles());

        userService.save(user);

        logService.newLog(String.format(LogConstants.LOG_USER_EDIT, admin.getLogin(),
                request.getLogin()), admin, LogType.SYSTEM);

        return ResponseEntity.ok(new MessageResponse("Пользователь успешно сохранен!"));
    }
}
