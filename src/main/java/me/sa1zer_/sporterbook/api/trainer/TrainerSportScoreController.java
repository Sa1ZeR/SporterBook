package me.sa1zer_.sporterbook.api.trainer;

import me.sa1zer_.sporterbook.domain.model.SportScore;
import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.request.trainer.UpdateSportEventRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.*;
import me.sa1zer_.sporterbook.utils.HttpUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/trainer/sportevent/")
@PreAuthorize("hasAuthority('TRAINER')")
public class TrainerSportScoreController {

    private final SportScoreService sportScoreService;
    private final UserService userService;
    private final TimeTableInfoService timeTableInfoService;
    private final SportSectionService sportSectionService;
    private final LogService logService;

    public TrainerSportScoreController(SportScoreService sportScoreService, UserService userService,
                                       TimeTableInfoService timeTableInfoService, SportSectionService sportSectionService, LogService logService) {
        this.sportScoreService = sportScoreService;
        this.userService = userService;
        this.timeTableInfoService = timeTableInfoService;
        this.sportSectionService = sportSectionService;
        this.logService = logService;
    }

    @PostMapping("update")
    public ResponseEntity<?> updateSportScore(@Valid @RequestBody UpdateSportEventRequest request,
                                              BindingResult result, Principal principal) {
        ResponseEntity<Object> err = HttpUtils.validBindingResult(result); //validation
        if (!ObjectUtils.isEmpty(err)) return err;
        //search section in db or create new if id equals null in request
        SportScore sportScore;
        if(request.getId() != null) {
            sportScore = sportScoreService.findById(request.getId());
        } else sportScore = new SportScore();
        //search objects in db;
        SportSection section = sportSectionService.findById(request.getSection());
        User trainer = userService.findByPrincipal(principal);
        if(!trainer.getTrainersSections().contains(section))
            return ResponseEntity.ok(new MessageResponse("Вы не ведете занятия в этой секции"));
        User student = userService.findById(request.getStudent());
        TimeTableInfo timeTableInfo = timeTableInfoService.findById(request.getTti());
        //set params
        if(StringUtils.hasText(request.getName()))
            sportScore.setName(request.getName());
        sportScore.setStudent(student);
        sportScore.setResult(request.getResult());
        sportScore.setMaxResult(request.getMaxResult());
        sportScore.setTrainer(trainer);
        sportScore.setTimeTableInfo(timeTableInfo);
        sportScore.setSportSection(section);
        //save in db
        sportScoreService.save(sportScore);
        //return response
        return ResponseEntity.ok(new MessageResponse("Норматив успешно сохранен!"));
    }
}
