package me.sa1zer_.sporterbook.api.admin;

import me.sa1zer_.sporterbook.domain.model.SportSection;
import me.sa1zer_.sporterbook.payload.facade.SportSectionMapper;
import me.sa1zer_.sporterbook.payload.request.SportSectionRequest;
import me.sa1zer_.sporterbook.payload.response.MessageResponse;
import me.sa1zer_.sporterbook.service.SportSectionService;
import me.sa1zer_.sporterbook.service.UserService;
import me.sa1zer_.sporterbook.utils.HttpUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/sportsection/")
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
        ResponseEntity<?> response = HttpUtils.validBindingResult(result);
        if(!ObjectUtils.isEmpty(response)) return response;

        if(request.getId() == null)
            sportSectionService.create(request.getName(), request.getDesc(), request.getPrice());
        else sportSectionService.updateByRequest(request);

        return ResponseEntity.ok(new MessageResponse("Секция успешно обновлена"));
    }

    @DeleteMapping("delete/{sId}")
    public ResponseEntity<?> delete(@PathVariable String sId) {
        SportSection section = sportSectionService.findById(Long.valueOf(sId));
        sportSectionService.delete(section);

        return ResponseEntity.ok(new MessageResponse("Секция успешно удалена"));
    }
}
