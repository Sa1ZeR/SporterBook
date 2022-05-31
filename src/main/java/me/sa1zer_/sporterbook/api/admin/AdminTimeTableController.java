package me.sa1zer_.sporterbook.api.admin;

import me.sa1zer_.sporterbook.domain.model.TimeTable;
import me.sa1zer_.sporterbook.payload.request.UpdateTimeTableRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.TimeTableService;
import me.sa1zer_.sporterbook.utils.HttpUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/timetable/")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminTimeTableController {

    private final TimeTableService timeTableService;

    public AdminTimeTableController(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @RequestMapping("update")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateTimeTableRequest request, BindingResult result) {
        ResponseEntity<Object> res = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(res)) return res;

        TimeTable timeTable;
        if(request.getId() == null)
            timeTable = timeTableService.create(request.getStart(), request.getEnd());
        else {
            timeTable = timeTableService.findById(request.getId());
            timeTable.setDateStart(request.getStart());
            timeTable.setDateEnd(request.getEnd());
            timeTableService.save(timeTable);
        }

        return ResponseEntity.ok(new MessageResponse("Расписание успешно сохранено!"));
    }
}
