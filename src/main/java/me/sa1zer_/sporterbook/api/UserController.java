package me.sa1zer_.sporterbook.api;

import me.sa1zer_.sporterbook.service.LogService;
import me.sa1zer_.sporterbook.service.UserService;

public class UserController {

    private final UserService userService;
    private final LogService logService;

    public UserController(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }
}
