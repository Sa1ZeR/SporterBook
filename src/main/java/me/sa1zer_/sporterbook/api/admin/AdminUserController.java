package me.sa1zer_.sporterbook.api.admin;

import me.sa1zer_.sporterbook.domain.LogConstants;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.enums.LogType;
import me.sa1zer_.sporterbook.payload.facade.UserFacade;
import me.sa1zer_.sporterbook.payload.request.admin.EditUserRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.LogService;
import me.sa1zer_.sporterbook.service.UserService;
import me.sa1zer_.sporterbook.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/admin/user/")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUserController {

    @Autowired
    private UserFacade userFacade;

    private final UserService userService;
    private final LogService logService;

    public AdminUserController(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @PostMapping("edit")
    public ResponseEntity<?> editUser(@Valid @RequestBody EditUserRequest request,
                                      BindingResult result, Principal principal) {
        ResponseEntity<Object> res = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(res)) return res;

        User user = userService.findById(request.getId());
        User admin = userService.findByPrincipal(principal);

        userService.updateByRequest(request);

        logService.newLog(String.format(LogConstants.LOG_USER_EDIT, admin.getLogin(),
                request.getLogin()), admin, LogType.SYSTEM);

        return ResponseEntity.ok(new MessageResponse("Пользователь успешно сохранен!"));
    }

    @PostMapping("delete/{uId}")
    public ResponseEntity<?> deleteUser(@PathVariable String uId, Principal principal) {
        User user = userService.findById(Long.valueOf(uId));
        User admin = userService.findByPrincipal(principal);

        userService.delete(user);

        logService.newLog(String.format(LogConstants.LOG_USER_DELETE, admin.getLogin(),
                user.getLogin()), admin, LogType.SYSTEM);
        return ResponseEntity.ok(new MessageResponse(String.format(
                "Пользователь %s успешно удален!", user.getLogin())));
    }

    @GetMapping("getUsers")
    public ResponseEntity<?> getUsers(@RequestParam int page, @RequestParam int count) {
        return ResponseEntity.ok(userService.findAll(PageRequest.of(page, count, Sort.by("id")
                .descending())).stream()
                .map(user -> userFacade.map(user)).toList());
    }
}
