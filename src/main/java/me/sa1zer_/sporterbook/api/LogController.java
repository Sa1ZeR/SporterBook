package me.sa1zer_.sporterbook.api;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.enums.LogType;
import me.sa1zer_.sporterbook.payload.facade.LogMapper;
import me.sa1zer_.sporterbook.service.LogService;
import me.sa1zer_.sporterbook.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Logging is performed using REST API requests.
 */
@RestController
@RequestMapping("api/logs")
public class LogController {

    private final UserService userService;
    private final LogService logService;
    private final LogMapper logMapper;

    public LogController(UserService userService, LogService logService, LogMapper logMapper) {
        this.userService = userService;
        this.logService = logService;
        this.logMapper = logMapper;
    }

    /**
     * GET request to all user logs.
     * @param page user's page number
     * @param num number of the room where the section takes place
     * @param logType type of logging
     * @param principal login or email user
     * @return status "OK" and list of user actions
     *
     * @see LogType
     */
    @GetMapping("getAll")
    public ResponseEntity<?> getAll(@RequestParam int page, @RequestParam int num,
                                    @RequestParam(required = false) LogType logType,
                                    Principal principal) {
        User user = userService.findByPrincipal(principal);
        if(logType == null) {
            return ResponseEntity.ok(logService.findAllByUser(user, PageRequest.of(page, num,
                    Sort.by("id").descending())).stream()
                    .map(logMapper::map).toList());
        }
        return ResponseEntity.ok(logService.findAllByUserAndLogType(user, logType,
                PageRequest.of(page, num, Sort.by("id").descending())).stream()
                .map(logMapper::map).toList());
    }
}
