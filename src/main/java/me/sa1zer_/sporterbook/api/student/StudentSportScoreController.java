package me.sa1zer_.sporterbook.api.student;

import me.sa1zer_.sporterbook.domain.model.SportScore;
import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.facade.SportScoreMapper;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.*;
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
@RequestMapping("/api/students/sportscore/")
@PreAuthorize("hasAuthority('STUDENT')")
public class StudentSportScoreController {

    private final UserService userService;
    private final SportScoreService sportScoreService;
    private final TimeTableInfoService timeTableInfoService;
    private final SportSectionService sportSectionService;
    private final SportScoreMapper sportScoreMapper;

    public StudentSportScoreController(UserService userService,
                                       SportScoreService sportScoreService,
                                       TimeTableInfoService timeTableInfoService, SportScoreMapper sportScoreMapper,
                                       LogService logService, SportSectionService sportSectionService) {
        this.userService = userService;
        this.sportScoreService = sportScoreService;
        this.timeTableInfoService = timeTableInfoService;
        this.sportScoreMapper = sportScoreMapper;
        this.sportSectionService = sportSectionService;
    }

    @GetMapping("getSportScore")
    public ResponseEntity<?> getSportScore(@RequestParam(name = "sId", required = false) Long sId,
                                           Principal principal) {
        User student = userService.findByPrincipal(principal);

        List<SportScore> eventResults;
        if(ObjectUtils.isEmpty(sId)) {
            eventResults = sportScoreService.findAllByStudent(student);
        } else {
            SportSection section = sportSectionService.findById(sId);
            if(!student.getSections().contains(section))
                return ResponseEntity.ok(new MessageResponse("Вы не занимаетесь в этой секции"));

            eventResults = sportScoreService.findAllByStudentAndSportSection(student, section);
        }

        return ResponseEntity.ok(eventResults.stream().map(sportScoreMapper::map).toList());
    }

}
