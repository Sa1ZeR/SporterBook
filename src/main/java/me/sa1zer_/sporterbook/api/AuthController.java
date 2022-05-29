package me.sa1zer_.sporterbook.api;

import me.sa1zer_.sporterbook.domain.LogConstants;
import me.sa1zer_.sporterbook.domain.model.enums.LogType;
import me.sa1zer_.sporterbook.payload.dto.UserDto;
import me.sa1zer_.sporterbook.payload.facade.UserFacade;
import me.sa1zer_.sporterbook.payload.response.SuccessLoginResponse;
import me.sa1zer_.sporterbook.security.jwt.JwtTokenProvider;
import me.sa1zer_.sporterbook.service.LogService;
import me.sa1zer_.sporterbook.service.UserService;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.request.SignInRequest;
import me.sa1zer_.sporterbook.payload.request.SignUpRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * User authentication is performed using REST API requests.
 *
 * @since 0.0.1-SNAPSHOT
 * @author Sa1ZeR
 * @version 1.0
 */
@RestController
@RequestMapping("/api/auth/")
@CrossOrigin("${common.cors-ip}")
public class AuthController {

    @Autowired
    private UserFacade userFacade;

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final LogService logService;

    /**
     * Create a {@link AuthController} instance with the given params.
     *
     * @param userService the service for checking and creating users
     * @param authenticationManager checking the compliance of the entered login data
     * @param tokenProvider Token provider used to authenticate requests
     * @param logService the service for logging users
     * @see UserService
     * @see JwtTokenProvider
     * @see LogService
     */
    public AuthController(UserService userService, AuthenticationManager authenticationManager,
                          JwtTokenProvider tokenProvider, LogService logService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.logService = logService;
    }

    /**
     * POST request to sing in.
     * <p>
     * example request:
     * <pre class="code">
     * POST http://localhost:3000/api/auth/signin
     * Content-Type: application/json
     *
     * {
     *   "login": "springdoc2022",
     *   "password": "parol12345678"
     * }
     * </pre>
     * @param request data for the user's login or email and password
     * @param result checks whether such a user exists (no need to send)
     * @return status "OK" if the user was registered and entered the correct data otherwise an error
     * @see SignInRequest
     */
    @PostMapping("signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest request, BindingResult result) {
        ResponseEntity<?> errors = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);

        User user = userService.findUserByLoginOrEmail(request.getLogin(), request.getLogin());
        logService.newLog(LogConstants.LOG_NEW_USER, user, LogType.COMMON);

        UserDto userDto = (UserDto) userFacade.map(user);
        return ResponseEntity.ok(new SuccessLoginResponse(userDto, token));
    }

    /**
     * POST request to sign up.
     * <p>
     * example request:
     * <pre class="code">
     * POST http://localhost:3000/api/auth/signup
     * Content-Type: application/json
     *
     * {
     *   "firstName": "familiay",
     *   "latName": "myname",
     *   "patronymic": "otchestvo",
     *   "login": "springdoc2022",
     *   "email": "springdoc2022@gmail.com",
     *   "password": "parol12345678",
     *   "passwordConfirm": "parol12345678",
     *   "phone": "78945613245",
     *   "birth": "1992.12.31",
     *   "sex": "1"
     * }
     * </pre>
     * @param request data for registration:
     *                first name;
     *                last name;
     *                patronymic;
     *                login;
     *                email;
     *                password;
     *                password confirm;
     *                phone;
     *                birth (yyyy.MM.dd);
     *                sex;
     * @param result checks whether such a user exists (no need to send)
     * @return status "OK" if all the registration data was filled in correctly and the entered
     * login and email do not match with the existing users otherwise an error
     * @see SignUpRequest
     */
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
                    new MessageResponse(400, "Пользователь с таким логином или email уже существует"));
        else user = userService.createUserFromRequest(request);

        logService.newLog(LogConstants.LOG_NEW_USER, user, LogType.COMMON);

        return ResponseEntity.ok(new MessageResponse(200, "Успешная регистрация!"));
    }
}
