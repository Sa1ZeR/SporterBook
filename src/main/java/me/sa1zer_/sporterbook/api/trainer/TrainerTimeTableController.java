package me.sa1zer_.sporterbook.api.trainer;

import me.sa1zer_.sporterbook.domain.model.*;
import me.sa1zer_.sporterbook.payload.facade.TimeTableInfoMapper;
import me.sa1zer_.sporterbook.payload.facade.UserMapper;
import me.sa1zer_.sporterbook.payload.request.trainer.AcceptVisitRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.*;
import me.sa1zer_.sporterbook.utils.HttpUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api/trainer/timetable/")
@PreAuthorize("hasAuthority('TRAINER')")
public class TrainerTimeTableController {

    private final UserService userService;
    private final TimeTableService timeTableService;
    private final TimeTableInfoService timeTableInfoService;
    private final TimeTableVisitService visitService;
    private final SportSectionService sportSectionService;
    private final UserMapper userMapper;
    private final TimeTableInfoMapper timeTableInfoMapper;

    public TrainerTimeTableController(UserService userService, TimeTableService timeTableService,
                                      TimeTableInfoService timeTableInfoService,
                                      TimeTableVisitService visitService,
                                      SportSectionService sportSectionService, UserMapper userMapper,
                                      TimeTableInfoMapper timeTableInfoMapper) {
        this.userService = userService;
        this.timeTableService = timeTableService;
        this.timeTableInfoService = timeTableInfoService;
        this.visitService = visitService;
        this.sportSectionService = sportSectionService;
        this.userMapper = userMapper;
        this.timeTableInfoMapper = timeTableInfoMapper;
    }

    @PostMapping("acceptVisit")
    public ResponseEntity<?> acceptVisit(@Valid @RequestBody AcceptVisitRequest request,
                                         BindingResult result) {
        ResponseEntity<Object> response = HttpUtils.validBindingResult(result); //error validations
        if(!ObjectUtils.isEmpty(response)) return response;

        User student = userService.findById(request.getStudent()); //search student in db
        TimeTableInfo timeTableInfo = timeTableInfoService.findById(request.getTtinfo()); //search tti in db
        if(request.getId() == null)
            visitService.create(student, timeTableInfo, request.isVisit()); //create new instance if id equals null
        else {
            TimeTabletVisit visitItem = visitService.findById(request.getId()); //if id not null, search ttv in db
            visitItem.setDate(timeTableInfo);
            visitItem.setStudent(student);
            visitItem.setVisit(request.isVisit());
            visitService.save(visitItem);
        }

        //return response
        return ResponseEntity.ok(new MessageResponse("Студент успешно отмечен!"));
    }

    @GetMapping("getStudents")
    public ResponseEntity<?> getStudents(@RequestParam Long sectionId) {
        SportSection section = sportSectionService.findById(sectionId);
        return ResponseEntity.ok(section.getStudents().stream().map(userMapper::map).toList());
    }

    @GetMapping("getTimeTable")
    public ResponseEntity<?> getTimeTable(Principal principal) {
        User trainer = userService.findByPrincipal(principal);
        Set<SportSection> sections = trainer.getTrainersSections();

        LocalDateTime now = LocalDateTime.now();
        while (now.getDayOfWeek() != DayOfWeek.SUNDAY){
            now = now.minusDays(1);
        }

        return ResponseEntity.ok(timeTableInfoService.findAllTimeTable(sections, now).stream()
                .map(timeTableInfoMapper::map).toList());
    }
}
