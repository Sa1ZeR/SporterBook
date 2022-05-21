package me.sa1zer_.sporterbook.api;

import me.sa1zer_.sporterbook.service.LogService;
import me.sa1zer_.sporterbook.service.TimeTableVisitService;
import me.sa1zer_.sporterbook.service.UserService;

public class TimeTableVisitController {

    private final TimeTableVisitService timeTableVisitService;
    private final UserService userService;
    private final LogService logService;

    public TimeTableVisitController(TimeTableVisitService timeTableVisitService,
                                    UserService userService,
                                    LogService logService) {
        this.timeTableVisitService = timeTableVisitService;
        this.userService = userService;
        this.logService = logService;
    }
}
