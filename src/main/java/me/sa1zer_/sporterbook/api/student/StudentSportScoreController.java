package me.sa1zer_.sporterbook.api.student;

import me.sa1zer_.sporterbook.domain.model.SportScore;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.facade.SportEventMapper;
import me.sa1zer_.sporterbook.payload.facade.SportSectionMapper;
import me.sa1zer_.sporterbook.service.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
public class StudentSportScoreController {

    private final UserService userService;
    private final SportScoreService sportScoreService;
    private final TimeTableInfoService timeTableInfoService;
    private final SportEventMapper eventMapper;

    public StudentSportScoreController(UserService userService,
                                       SportScoreService sportScoreService,
                                       TimeTableInfoService timeTableInfoService, SportEventMapper eventMapper,
                                       LogService logService) {
        this.userService = userService;
        this.sportScoreService = sportScoreService;
        this.timeTableInfoService = timeTableInfoService;
        this.eventMapper = eventMapper;
    }

    @GetMapping("getSportScore")
    public ResponseEntity<?> getSportScore(@RequestParam(name = "sId", required = false) Long sId,
                                           Principal principal) {
        User student = userService.findByPrincipal(principal);

        List<SportScore> eventResults;
        if(ObjectUtils.isEmpty(sId)) {
            eventResults = sportScoreService.findAllByStudent(student);
        } else {
            TimeTableInfo ttInfo = timeTableInfoService.findById(sId);
            eventResults = sportScoreService.findAllByStudentAndTimeTableInfo(student, ttInfo);
        }

        return ResponseEntity.ok(eventResults.stream().map(eventMapper::map).toList());
    }

}
