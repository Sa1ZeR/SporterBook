package me.sa1zer_.sporterbook.api;

import me.sa1zer_.sporterbook.payload.response.SuccessLoginResponse;
import me.sa1zer_.sporterbook.security.jwt.JwtTokenProvider;
import me.sa1zer_.sporterbook.service.UserService;
import me.sa1zer_.sporterbook.model.User;
import me.sa1zer_.sporterbook.payload.request.SignInRequest;
import me.sa1zer_.sporterbook.payload.request.SignUpRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.utils.HttpUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin("${common.cors-ip}")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }


    @PostMapping("signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest request, BindingResult result) {
        ResponseEntity<?> errors = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new SuccessLoginResponse(token));
    }

    @PostMapping("signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request, BindingResult result) {
        ResponseEntity<?> errors = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        if(!request.getPassword().equals(request.getPasswordConfirm()))
            return ResponseEntity.badRequest().body(
                    new MessageResponse(400, "Пароли должны совпадать"));

        User user = userService.getUserByLoginOrEmail(request.getLogin(), request.getEmail());
        if(!ObjectUtils.isEmpty(user))
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Пользователь с таким логином или email уже существует"));
        else user = userService.createUserFromRequest(request);
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse(200, "Успешная регистрация!"));
    }
}
