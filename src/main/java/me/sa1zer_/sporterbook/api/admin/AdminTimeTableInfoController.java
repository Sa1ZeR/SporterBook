package me.sa1zer_.sporterbook.api.admin;

import me.sa1zer_.sporterbook.domain.model.*;
import me.sa1zer_.sporterbook.payload.request.admin.UpdateTimeTableInfoRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.*;
import me.sa1zer_.sporterbook.utils.HttpUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/timetableinfo/")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminTimeTableInfoController {

    private final TimeTableInfoService timeTableInfoService;
    private final TimeTableService timeTableService;
    private final TimeTableCategoryService categoryService;
    private final SportSectionService sportSectionService;
    private final RoomService roomService;

    public AdminTimeTableInfoController(TimeTableInfoService timeTableInfoService,
                                        TimeTableService timeTableService,
                                        TimeTableCategoryService categoryService,
                                        SportSectionService sportSectionService, RoomService roomService) {
        this.timeTableInfoService = timeTableInfoService;
        this.timeTableService = timeTableService;
        this.categoryService = categoryService;
        this.sportSectionService = sportSectionService;
        this.roomService = roomService;
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateTimeTableInfoRequest request,
                                    BindingResult result) {
        ResponseEntity<Object> res = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(res)) return res;

        TimeTableInfo timeTableInfo;

        TimeTable timeTable = timeTableService.findById(request.getTimetable());
        SportSection sportSection = sportSectionService.findById(request.getSection());
        TimeTableCategory timeTableCategory = categoryService.findById(request.getCategory());
        Room room = roomService.findById(request.getRoom());

        if(request.getId() == null) {
            timeTableInfo = timeTableInfoService.create(timeTable, sportSection, room, timeTableCategory);
        } else {
            timeTableInfo = timeTableInfoService.findById(request.getId());

            timeTableInfo.setTimeTable(timeTable);
            timeTableInfo.setSection(sportSection);
            timeTableInfo.setCategory(timeTableCategory);
            timeTableInfo.setRoom(room);

            timeTableInfoService.save(timeTableInfo);
        }

        return ResponseEntity.ok(new MessageResponse("Информация для расписания сохранена"));
    }


}
