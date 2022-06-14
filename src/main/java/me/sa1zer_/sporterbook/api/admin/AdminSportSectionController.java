package me.sa1zer_.sporterbook.api.admin;

import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import me.sa1zer_.sporterbook.payload.facade.SportSectionMapper;
import me.sa1zer_.sporterbook.payload.request.admin.AcceptStudentRequest;
import me.sa1zer_.sporterbook.payload.request.admin.AddStudentToSectionRequest;
import me.sa1zer_.sporterbook.payload.request.admin.AddTrainerToSectionRequest;
import me.sa1zer_.sporterbook.payload.request.admin.SportSectionRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.SportSectionService;
import me.sa1zer_.sporterbook.service.UserService;
import me.sa1zer_.sporterbook.utils.HttpUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/admin/sportsection/")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminSportSectionController {


    private final UserService userService;
    private final SportSectionService sportSectionService;
    private final SportSectionMapper mapper;

    public AdminSportSectionController(UserService userService,
                                       SportSectionService sportSectionService,
                                       SportSectionMapper mapper) {
        this.userService = userService;
        this.sportSectionService = sportSectionService;
        this.mapper = mapper;
    }

    @PostMapping("update")
    public ResponseEntity<?> updateSportSection(@Valid @RequestBody SportSectionRequest request,
                                                     BindingResult result) {
        //error validation
        ResponseEntity<?> response = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(response)) return response;

        // create new section if id equals null, update exist section
        if(request.getId() == null)
            sportSectionService.create(request.getName(), request.getDesc(), request.getPrice());
        else sportSectionService.updateByRequest(request);

        //return response
        return ResponseEntity.ok(new MessageResponse("Секция успешно сохранена!"));
    }

    @DeleteMapping("delete/{sId}")
    public ResponseEntity<?> delete(@PathVariable String sId) {
        SportSection section = sportSectionService.findById(Long.valueOf(sId));
        sportSectionService.delete(section);

        return ResponseEntity.ok(new MessageResponse("Секция успешно удалена"));
    }

    @PostMapping("addTrainer")
    public ResponseEntity<?> addTrainer(@Valid @RequestBody AddTrainerToSectionRequest request,
                                        BindingResult result) {
        //error validation
        ResponseEntity<Object> res = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(res)) return res;

        //search section in db
        SportSection section = sportSectionService.findById(request.getSection());
        //add trainer to section
        section.getTrainers().add(userService.findById(request.getTrainer()));
        //save section in db
        sportSectionService.save(section);

        //return response
        return ResponseEntity.ok(new MessageResponse("Тренер успешно добавлен!"));
    }

    @PostMapping("addStudent")
    public ResponseEntity<?> addStudent(@Valid @RequestBody AddStudentToSectionRequest request,
                                        BindingResult result) {
        ResponseEntity<Object> res = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(res)) return res;

        SportSection section = sportSectionService.findById(request.getSection());
        section.getStudents().add(userService.findById(request.getStudent()));
        sportSectionService.save(section);

        return ResponseEntity.ok(new MessageResponse("Студент успешно добавлен!"));
    }

    @PostMapping("acceptStudent")
    public ResponseEntity<?> changeGroup(@Valid @RequestBody AcceptStudentRequest request,
                                         BindingResult result) {
        ResponseEntity<Object> res = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(res)) return res;

        SportSection sportSection = sportSectionService.findById(request.getId());
        User user = userService.findById(request.getStudent());

        if(!sportSection.getRequests().contains(user))
            return ResponseEntity.ok(new MessageResponse("Данный студент не подал заявку на вступление"));

        sportSection.getStudents().add(user);
        user.getRoles().add(Role.STUDENT);
        sportSection.getRequests().remove(user);

        userService.save(user);
        sportSectionService.save(sportSection);

        return ResponseEntity.ok(new MessageResponse("Вы успешно приняли запрос на вступление!"));
    }
}
