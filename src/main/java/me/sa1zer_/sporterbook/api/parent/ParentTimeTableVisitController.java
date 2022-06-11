package me.sa1zer_.sporterbook.api.parent;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.dto.TimeTableVisitDto;
import me.sa1zer_.sporterbook.payload.dto.UserDto;
import me.sa1zer_.sporterbook.payload.facade.TimeTableVisitMapper;
import me.sa1zer_.sporterbook.payload.facade.UserMapper;
import me.sa1zer_.sporterbook.service.TimeTableVisitService;
import me.sa1zer_.sporterbook.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/parent/visits/")
@PreAuthorize("hasAuthority('PARENT')")
public class ParentTimeTableVisitController {

    private final UserService userService;
    private final TimeTableVisitService timeTableVisitService;
    private final TimeTableVisitMapper timeTableVisitMapper;
    private final UserMapper userMapper;

    public ParentTimeTableVisitController(UserService userService, TimeTableVisitService timeTableVisitService,
                                          TimeTableVisitMapper timeTableVisitMapper, UserMapper userMapper) {
        this.userService = userService;
        this.timeTableVisitService = timeTableVisitService;
        this.timeTableVisitMapper = timeTableVisitMapper;
        this.userMapper = userMapper;
    }

    @GetMapping("getVisits")
    public ResponseEntity<?> getVisits(@RequestParam(name = "visit") boolean visit, Principal principal) {
        User parent = userService.findByPrincipal(principal);

        Set<User> children = parent.getChildren();

        HashMap<UserDto, List<TimeTableVisitDto>> visits = new HashMap<>();

        for(User student : children) {
            List<TimeTableVisitDto> timeTableVisitDtos = timeTableVisitService.
                    findAllByStudentAndVisit(student, visit).stream()
                    .map(timeTableVisitMapper::map).toList();

            visits.put((UserDto) userMapper.map(student), timeTableVisitDtos);
        }

        return ResponseEntity.ok(visit);
    }
}
