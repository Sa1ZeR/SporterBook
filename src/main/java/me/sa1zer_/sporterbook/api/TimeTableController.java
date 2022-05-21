package me.sa1zer_.sporterbook.api;

import me.sa1zer_.sporterbook.service.*;

public class TimeTableController {

    private final TimeTableService timeTableService;
    private final TimeTableCategoryService timeTableCategoryService;
    private final TimeTableInfoService timeTableInfoService;
    private final UserService userService;
    private final LogService logService;

    public TimeTableController(TimeTableService timeTableService,
                               TimeTableCategoryService timeTableCategoryService,
                               TimeTableInfoService timeTableInfoService, UserService userService,
                               LogService logService) {
        this.timeTableService = timeTableService;
        this.timeTableCategoryService = timeTableCategoryService;
        this.timeTableInfoService = timeTableInfoService;
        this.userService = userService;
        this.logService = logService;
    }
}
