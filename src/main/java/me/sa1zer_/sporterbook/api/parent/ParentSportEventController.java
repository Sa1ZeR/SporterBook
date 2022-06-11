package me.sa1zer_.sporterbook.api.parent;

import me.sa1zer_.sporterbook.domain.model.SportEvent;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.dto.SportEventDto;
import me.sa1zer_.sporterbook.payload.dto.UserDto;
import me.sa1zer_.sporterbook.payload.facade.SportEventMapper;
import me.sa1zer_.sporterbook.payload.facade.UserMapper;
import me.sa1zer_.sporterbook.service.SportEventService;
import me.sa1zer_.sporterbook.service.TimeTableInfoService;
import me.sa1zer_.sporterbook.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/parent/timetable/")
@PreAuthorize("hasAuthority('PARENT')")
public class ParentSportEventController {

    private final UserService userService;
    private final SportEventService sportEventService;
    private final TimeTableInfoService timeTableInfoService;
    private final SportEventMapper eventMapper;
    private final UserMapper userMapper;

    public ParentSportEventController(UserService userService, SportEventService sportEventService,
                                      TimeTableInfoService timeTableInfoService, SportEventMapper eventMapper,
                                      UserMapper userMapper) {
        this.userService = userService;
        this.sportEventService = sportEventService;
        this.timeTableInfoService = timeTableInfoService;
        this.eventMapper = eventMapper;
        this.userMapper = userMapper;
    }

    @GetMapping("getSportEvents")
    public ResponseEntity<?> getSportEvents(@RequestParam(name = "sId", required = false) Long sId,
                                            Principal principal) {
        User parent = userService.findByPrincipal(principal);
        Set<User> children = parent.getChildren();

        HashMap<UserDto, List<SportEventDto>> results = new HashMap<>();

        for (User student : children) {
            List<SportEvent> eventResults;
            if (ObjectUtils.isEmpty(sId)) {
                eventResults = sportEventService.findAllByStudent(student);
            } else {
                TimeTableInfo ttInfo = timeTableInfoService.findById(sId);
                eventResults = sportEventService.findAllByStudentAndTimeTableInfo(student, ttInfo);
            }
            results.put((UserDto) userMapper.map(student),
                    eventResults.stream().map(eventMapper::map).toList());
        }

        return ResponseEntity.ok(results);
    }
}
