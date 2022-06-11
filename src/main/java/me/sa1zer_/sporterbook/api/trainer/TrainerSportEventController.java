package me.sa1zer_.sporterbook.api.trainer;

import me.sa1zer_.sporterbook.domain.model.SportEvent;
import me.sa1zer_.sporterbook.domain.model.TimeTableInfo;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.request.trainer.UpdateSportEventRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.SportEventService;
import me.sa1zer_.sporterbook.service.TimeTableInfoService;
import me.sa1zer_.sporterbook.service.UserService;
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
public class TrainerSportEventController {

    private final SportEventService sportEventService;
    private final UserService userService;
    private final TimeTableInfoService timeTableInfoService;

    public TrainerSportEventController(SportEventService sportEventService, UserService userService,
                                       TimeTableInfoService timeTableInfoService) {
        this.sportEventService = sportEventService;
        this.userService = userService;
        this.timeTableInfoService = timeTableInfoService;
    }

    @PostMapping("update")
    public ResponseEntity<?> updateSportEvent(@Valid @RequestBody UpdateSportEventRequest request,
                                              BindingResult result, Principal principal) {
        ResponseEntity<Object> err = HttpUtils.validBindingResult(result);
        if (!ObjectUtils.isEmpty(err)) return err;

        SportEvent sportEvent;
        if(request.getId() != null) {
            sportEvent = sportEventService.findById(request.getId());
        } else sportEvent = new SportEvent();

        User trainer = userService.findByPrincipal(principal);
        User student = userService.findById(request.getStudent());
        TimeTableInfo timeTableInfo = timeTableInfoService.findById(request.getTti());

        if(StringUtils.hasText(request.getName()))
            sportEvent.setName(request.getName());

        sportEvent.setStudent(student);
        sportEvent.setResult(request.getResult());
        sportEvent.setMaxResult(request.getMaxResult());
        sportEvent.setTrainer(trainer);
        sportEvent.setTimeTableInfo(timeTableInfo);

        sportEventService.save(sportEvent);

        return ResponseEntity.ok(new MessageResponse("Норматив успешно сохранен!"));
    }
}
