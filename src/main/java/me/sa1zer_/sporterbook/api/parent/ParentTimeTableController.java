package me.sa1zer_.sporterbook.api.parent;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.dto.TimeTableInfoDto;
import me.sa1zer_.sporterbook.payload.dto.UserDto;
import me.sa1zer_.sporterbook.payload.facade.TimeTableInfoMapper;
import me.sa1zer_.sporterbook.payload.facade.UserMapper;
import me.sa1zer_.sporterbook.service.TimeTableInfoService;
import me.sa1zer_.sporterbook.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/parent/timetable/")
@PreAuthorize("hasAuthority('PARENT')")
public class ParentTimeTableController {

    private final UserService userService;
    private final TimeTableInfoService timeTableInfoService;
    private final TimeTableInfoMapper timeTableInfoMapper;
    private final UserMapper userMapper;

    public ParentTimeTableController(UserService userService, TimeTableInfoService timeTableInfoService,
                                     TimeTableInfoMapper timeTableMapper1, UserMapper userMapper) {
        this.userService = userService;
        this.timeTableInfoService = timeTableInfoService;
        this.timeTableInfoMapper = timeTableMapper1;
        this.userMapper = userMapper;
    }

    @GetMapping("getTimeTable")
    public ResponseEntity<?> getTimeTable(Principal principal) {
        User parent = userService.findByPrincipal(principal);

        LocalDateTime now = LocalDateTime.now();
        while (now.getDayOfWeek() != DayOfWeek.SUNDAY){
            now = now.minusDays(1);
        }

        HashMap<UserDto, List<TimeTableInfoDto>> timetable = new HashMap<>();

        Set<User> children = parent.getChildren();

        for (User student : children) {
            List<TimeTableInfoDto> timeTableInfoDtos = timeTableInfoService.findAllTimeTable(student.getSections(),
                    now).stream().map(timeTableInfoMapper::map).toList();
            timetable.put((UserDto) userMapper.map(student), timeTableInfoDtos);
        }

        return ResponseEntity.ok(timetable);
    }
}
