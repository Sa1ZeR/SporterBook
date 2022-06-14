package me.sa1zer_.sporterbook.api.parent;

import me.sa1zer_.sporterbook.domain.model.SportScore;
import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.dto.SportEventDto;
import me.sa1zer_.sporterbook.payload.dto.UserDto;
import me.sa1zer_.sporterbook.payload.facade.SportScoreMapper;
import me.sa1zer_.sporterbook.payload.facade.UserMapper;
import me.sa1zer_.sporterbook.service.SportScoreService;
import me.sa1zer_.sporterbook.service.SportSectionService;
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
@RequestMapping("/api/parent/sportscore/")
@PreAuthorize("hasAuthority('PARENT')")
public class ParentSportScoreController {

    private final UserService userService;
    private final SportScoreService sportScoreService;
    private final TimeTableInfoService timeTableInfoService;
    private final SportSectionService sportSectionService;
    private final SportScoreMapper eventMapper;
    private final UserMapper userMapper;

    public ParentSportScoreController(UserService userService, SportScoreService sportScoreService,
                                      TimeTableInfoService timeTableInfoService,
                                      SportSectionService sportSectionService, SportScoreMapper eventMapper,
                                      UserMapper userMapper) {
        this.userService = userService;
        this.sportScoreService = sportScoreService;
        this.timeTableInfoService = timeTableInfoService;
        this.sportSectionService = sportSectionService;
        this.eventMapper = eventMapper;
        this.userMapper = userMapper;
    }

    @GetMapping("getSportScore")
    public ResponseEntity<?> getSportScore(@RequestParam(name = "sId", required = false) Long sId,
                                            Principal principal) {
        User parent = userService.findByPrincipal(principal); //get user from seesion
        Set<User> children = parent.getChildren(); // get children

        HashMap<UserDto, List<SportEventDto>> results = new HashMap<>();

        //get score
        for (User student : children) {
            List<SportScore> eventResults;
            if (!ObjectUtils.isEmpty(sId)) {
                SportSection sportSection = sportSectionService.findById(sId);
                eventResults = sportScoreService.findAllByStudentAndSportSection(student, sportSection);
            } else {
                eventResults = sportScoreService.findAllByStudent(student);
            }
            results.put((UserDto) userMapper.map(student),
                    eventResults.stream().map(eventMapper::map).toList());
        }
        //return response
        return ResponseEntity.ok(results);
    }
}
