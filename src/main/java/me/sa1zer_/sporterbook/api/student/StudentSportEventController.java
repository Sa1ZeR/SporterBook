package me.sa1zer_.sporterbook.api.student;

import me.sa1zer_.sporterbook.domain.model.SportEvent;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.facade.SportEventMapper;
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
import java.util.List;

@RestController
@RequestMapping("/api/students/sportevent/")
@PreAuthorize("hasAuthority('STUDENT')")
public class StudentSportEventController {

    private final UserService userService;
    private final SportEventService sportEventService;
    private final TimeTableInfoService timeTableInfoService;
    private final SportEventMapper eventMapper;

    public StudentSportEventController(UserService userService, SportEventService sportEventService,
                                       TimeTableInfoService timeTableInfoService, SportEventMapper eventMapper) {
        this.userService = userService;
        this.sportEventService = sportEventService;
        this.timeTableInfoService = timeTableInfoService;
        this.eventMapper = eventMapper;
    }

    @GetMapping("getSportEvents")
    public ResponseEntity<?> getSportEvents(@RequestParam(name = "sId", required = false) Long sId,
                                            Principal principal) {
        User student = userService.findByPrincipal(principal);

        List<SportEvent> eventResults;
        if(ObjectUtils.isEmpty(sId)) {
            eventResults = sportEventService.findAllByStudent(student);
        } else {
            TimeTableInfo ttInfo = timeTableInfoService.findById(sId);
            eventResults = sportEventService.findAllByStudentAndTimeTableInfo(student, ttInfo);
        }

        return ResponseEntity.ok(eventResults.stream().map(eventMapper::map).toList());
    }
}
