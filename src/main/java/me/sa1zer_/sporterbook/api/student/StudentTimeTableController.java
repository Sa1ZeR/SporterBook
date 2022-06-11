package me.sa1zer_.sporterbook.api.student;

import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.facade.TimeTableInfoMapper;
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
import java.util.Set;

@RestController
@RequestMapping("/api/students/timetable/")
@PreAuthorize("hasAuthority('STUDENT')")
public class StudentTimeTableController {

    private final UserService userService;
    private final TimeTableInfoService timeTableInfoService;
    private final TimeTableInfoMapper timeTableInfoMapper;

    public StudentTimeTableController(UserService userService, TimeTableInfoService timeTableInfoService, TimeTableInfoMapper timeTableMapper1) {
        this.userService = userService;
        this.timeTableInfoService = timeTableInfoService;
        this.timeTableInfoMapper = timeTableMapper1;
    }

    @GetMapping("getTimeTable")
    public ResponseEntity<?> getTimeTable(Principal principal) {
        User student = userService.findByPrincipal(principal);
        Set<SportSection> sections = student.getSections();

        LocalDateTime now = LocalDateTime.now();
        while (now.getDayOfWeek() != DayOfWeek.SUNDAY){
            now = now.minusDays(1);
        }

        return ResponseEntity.ok(timeTableInfoService.findAllTimeTable(sections, now).stream()
                .map(timeTableInfoMapper::map).toList());
    }
}
