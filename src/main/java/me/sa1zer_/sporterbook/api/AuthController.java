package me.sa1zer_.sporterbook.api;

import me.sa1zer_.sporterbook.service.UserService;
import me.sa1zer_.sporterbook.model.User;
import me.sa1zer_.sporterbook.payload.request.SignInRequest;
import me.sa1zer_.sporterbook.payload.request.SignUpRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.utils.HttpUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin("${common.cors-ip}")
public class AuthController {

    private final UserService userRepository;

    public AuthController(UserService userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("signIn")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest request, BindingResult result) {

        return ResponseEntity.ok("");
    }

    @PostMapping("signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request, BindingResult result) {
        ResponseEntity<?> errors = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        if(!request.getPassword().equals(request.getPasswordConfirm()))
            return ResponseEntity.badRequest().body(
                    new MessageResponse(400, "Пароли должны совпадать"));

        User user = userRepository.getUserByLoginOrEmail(request.getLogin(), request.getEmail());
        if(!ObjectUtils.isEmpty(user))
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Пользователь с таким логином или email уже существует"));
        else user = new User();





        return ResponseEntity.ok("");
    }
}
